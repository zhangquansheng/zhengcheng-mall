package com.zhengcheng.mall.api.controller.facade.internal;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.zhengcheng.mall.service.UserLoginLogService;
import com.zhengcheng.mall.service.UserService;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.net.NetUtil;
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
                    .serverIp(NetUtil.getLocalhostStr()).loginIp(IpAddressUtils.getIpAddress(request))
                    .result(LoginResultEnum.FAILURE).content("用户已被禁用！").build());
            throw new BizException("用户已被禁用！");
        }

        // sa-token.dev33.cn 登录
        StpUtil.login(user.getId());

        user.setLastLogin(LocalDateTime.now());
        userService.updateById(user);

        userLoginLogService.save(UserLoginLog.builder().userId(user.getId()).type(LoginTypeEnum.LOGIN)
                .serverIp(NetUtil.getLocalhostStr()).loginIp(IpAddressUtils.getIpAddress(request))
                .result(LoginResultEnum.SUCCESS).build());
        return StpUtil.getTokenInfo();
    }

}
