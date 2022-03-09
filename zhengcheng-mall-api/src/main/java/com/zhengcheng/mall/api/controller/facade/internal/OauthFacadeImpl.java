package com.zhengcheng.mall.api.controller.facade.internal;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhengcheng.common.exception.BizException;
import com.zhengcheng.core.util.IpAddressUtils;
import com.zhengcheng.mall.api.controller.facade.OauthFacade;
import com.zhengcheng.mall.domain.entity.User;
import com.zhengcheng.mall.domain.entity.UserLoginLog;
import com.zhengcheng.mall.domain.enums.LoginResultEnum;
import com.zhengcheng.mall.domain.enums.LoginTypeEnum;
import com.zhengcheng.mall.service.UserLoginLogService;
import com.zhengcheng.mall.service.UserService;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.net.NetUtil;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.crypto.asymmetric.RSA;
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
    private UserService userService;
    @Autowired
    private UserLoginLogService userLoginLogService;
    @Autowired
    private RSA rsa;

    @Override
    public SaTokenInfo login(String username, String enPassword, HttpServletRequest request) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        String password = new String(rsa.decrypt(Base64.decode(enPassword), KeyType.PrivateKey));
        return userLogin(user, password, request);
    }

    private SaTokenInfo userLogin(User user, String password, HttpServletRequest request) {
        if (Objects.isNull(user)) {
            throw new BizException("用户名或密码错误！");
        }

        if (!userService.isSamePassword(password, user.getPassword())) {
            throw new BizException("用户名或密码错误！");
        }

        if (user.isDisabled()) {
            userLoginLogService.save(UserLoginLog.builder().userId(user.getId()).type(LoginTypeEnum.LOGIN)
                .serverIp(NetUtil.getLocalhostStr()).loginIp(IpAddressUtils.getIpAddress(request))
                .result(LoginResultEnum.FAILURE).content("用户已被禁用！").build());
            throw new BizException("用户已被禁用！");
        }

        StpUtil.login(user.getId());
        userLoginLogService.save(
            UserLoginLog.builder().userId(user.getId()).type(LoginTypeEnum.LOGIN).serverIp(NetUtil.getLocalhostStr())
                .loginIp(IpAddressUtils.getIpAddress(request)).result(LoginResultEnum.SUCCESS).build());
        return StpUtil.getTokenInfo();
    }
}
