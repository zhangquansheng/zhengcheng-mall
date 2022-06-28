package com.zhengcheng.mall.api.controller.facade.internal;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhengcheng.common.exception.BizException;
import com.zhengcheng.common.utils.IpAddressUtil;
import com.zhengcheng.mall.api.controller.facade.OauthFacade;
import com.zhengcheng.mall.common.PasswordRsaUtil;
import com.zhengcheng.mall.domain.entity.User;
import com.zhengcheng.mall.domain.entity.UserLoginLog;
import com.zhengcheng.mall.domain.enums.LoginResultEnum;
import com.zhengcheng.mall.domain.enums.LoginTypeEnum;
import com.zhengcheng.mall.service.UserLoginLogService;
import com.zhengcheng.mall.service.UserService;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * UserFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2021/7/15 14:28
 */
@Slf4j
@Service
public class OauthFacadeImpl implements OauthFacade {

    @Autowired
    private UserService           userService;
    @Autowired
    private StringRedisTemplate   stringRedisTemplate;
    @Autowired
    private UserLoginLogService   userLoginLogService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PasswordRsaUtil       passwordRsaUtil;

    @Override
    public SaTokenInfo login(String username, String enPassword, HttpServletRequest request) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (Objects.isNull(user)) {
            throw new BizException("用户名或密码错误！");
        }

        String password = passwordRsaUtil.decrypt(enPassword);
        if (bCryptPasswordEncoder.matches(SecureUtil.md5(password), user.getPassword())) {
            throw new BizException("用户名或密码错误！");
        }

        if (Boolean.FALSE.equals(user.getEnable())) {
            userLoginLogService.save(UserLoginLog.builder().userId(user.getId()).type(LoginTypeEnum.LOGIN)
                    .serverIp(NetUtil.getLocalhostStr()).loginIp(IpAddressUtil.getIpAddress(request))
                    .result(LoginResultEnum.FAILURE).content("用户已被禁用！").build());
            throw new BizException("用户已被禁用！");
        }

        // sa-token.dev33.cn 登录
        StpUtil.login(user.getId());

        user.setLastLogin(LocalDateTime.now());
        userService.updateById(user);

        String userActivityKeyPrefix = "zc:user:act:bm:";
        String dayKey = StrUtil.format("{}{}", userActivityKeyPrefix, DateUtil.format(new Date(), PURE_DATE_FORMAT));
        // 每天按日期生成一个位图
        stringRedisTemplate.opsForValue().setBit(dayKey, user.getId(), Boolean.TRUE);

        // 日活
        Long dayActivityNum = stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(dayKey.getBytes()));
        log.info("用户日活: {}", dayActivityNum);

        // 月活
        int maxDayNum = 30;
        byte[][] bytes = new byte[maxDayNum][];
        for (int offset = 0; offset < maxDayNum; offset++) {
            bytes[offset] = StrUtil.format("{}{}", userActivityKeyPrefix,
                    DateUtil.format(DateUtil.offsetDay(new Date(), -offset), PURE_DATE_FORMAT)).getBytes();
        }
        String monthKey = StrUtil.format("{}{}", userActivityKeyPrefix, 30);
        stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(RedisStringCommands.BitOperation.OR,
                monthKey.getBytes(), bytes));
        Long monthActivityNum = stringRedisTemplate
                .execute((RedisCallback<Long>) con -> con.bitCount(monthKey.getBytes()));
        log.info("用户月活：{}", monthActivityNum);

        userLoginLogService.save(UserLoginLog.builder().userId(user.getId()).type(LoginTypeEnum.LOGIN)
                .serverIp(NetUtil.getLocalhostStr()).loginIp(IpAddressUtil.getIpAddress(request))
                .result(LoginResultEnum.SUCCESS).build());
        return StpUtil.getTokenInfo();
    }

}
