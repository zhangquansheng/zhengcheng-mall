package com.zhengcheng.mall.api.controller.facade.internal;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhengcheng.common.exception.BizException;
import com.zhengcheng.core.util.IpAddressUtils;
import com.zhengcheng.mall.api.controller.facade.OauthFacade;
import com.zhengcheng.mall.common.PasswordRsaUtil;
import com.zhengcheng.mall.domain.entity.User;
import com.zhengcheng.mall.domain.entity.UserLoginLog;
import com.zhengcheng.mall.domain.enums.LoginResultEnum;
import com.zhengcheng.mall.domain.enums.LoginTypeEnum;
import com.zhengcheng.mall.service.UserActivityStatisticsService;
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
    private UserService                   userService;
    @Autowired
    private UserActivityStatisticsService userActivityStatisticsService;
    @Autowired
    private UserLoginLogService           userLoginLogService;
    @Autowired
    private BCryptPasswordEncoder         bCryptPasswordEncoder;
    @Autowired
    private PasswordRsaUtil               passwordRsaUtil;

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
                    .serverIp(NetUtil.getLocalhostStr()).loginIp(IpAddressUtils.getIpAddress(request))
                    .result(LoginResultEnum.FAILURE).content("用户已被禁用！").build());
            throw new BizException("用户已被禁用！");
        }

        // sa-token.dev33.cn 登录
        StpUtil.login(user.getId());

        user.setLastLogin(LocalDateTime.now());
        userService.updateById(user);

        String key = StrUtil.format("zc:user:act:bm:{}", DateUtil.format(new Date(), PURE_DATE_FORMAT));
        userActivityStatisticsService.setBit(key, user.getId(), Boolean.TRUE);

        // 日活
        long dayActivityNum = userActivityStatisticsService.bitCount(key);
        log.info("用户日活: {}", dayActivityNum);

        // 月活
        String[] keys = new String[30];
        for (int offset = 0; offset < 30; offset++) {
            keys[offset] = StrUtil.format("zc:user:act:bm:{}",
                    DateUtil.format(DateUtil.offsetDay(new Date(), -offset), PURE_DATE_FORMAT));
        }
        log.info("用户月活：{}", userActivityStatisticsService.bitOpResult(RedisStringCommands.BitOperation.OR,
                StrUtil.format("zc:user:act:bm:last:30"), keys));

        // 留存率
        String yesterdayKey = StrUtil.format("zc:user:act:bm:{}",
                DateUtil.format(DateUtil.offsetDay(new Date(), -1), PURE_DATE_FORMAT));
        log.info("用户留存率：{}%", userActivityStatisticsService.bitOpResult(RedisStringCommands.BitOperation.AND,
                StrUtil.format("zc:user:act:bm:cn:2"), yesterdayKey, key) * 100 / dayActivityNum);

        userLoginLogService.save(UserLoginLog.builder().userId(user.getId()).type(LoginTypeEnum.LOGIN)
                .serverIp(NetUtil.getLocalhostStr()).loginIp(IpAddressUtils.getIpAddress(request))
                .result(LoginResultEnum.SUCCESS).build());
        return StpUtil.getTokenInfo();
    }

}
