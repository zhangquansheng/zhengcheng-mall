package com.zhengcheng.mall.api.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.controller.facade.OauthFacade;
import com.zhengcheng.mall.api.feign.OauthFeign;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.ApiOperation;

/**
 * 令牌申请接口
 *
 * @author quansheng1.zhang
 * @since 2021/7/15 14:20
 */
@RestController
@RequestMapping("/oauth")
public class OauthController implements OauthFeign {

    @Autowired
    private OauthFacade userFacade;

    @ApiOperation(value = "注销登录")
    @GetMapping(value = "/logout")
    @Override
    public Result<Void> logout() {
        StpUtil.logout();
        return Result.success();
    }

    @ApiOperation(value = "指定token的会话注销登录")
    @GetMapping(value = "/logoutByToken")
    @Override
    public Result<Void> logoutByToken(@RequestParam("access_token") String accessToken) {
        StpUtil.logoutByTokenValue(accessToken);
        return Result.success();
    }

    @ApiOperation(value = "password获取token")
    @GetMapping("/token")
    @Override
    public Result<SaTokenInfo> getToken(@RequestParam("username") String username,
        @RequestParam("enPassword") String enPassword) {
        return Result.successData(userFacade.login(username, enPassword, getRequest()));
    }

    @ApiOperation(value = "password获取token")
    @PostMapping("/token")
    @Override
    public Result<SaTokenInfo> postToken(@RequestParam("username") String username,
        @RequestParam("password") String password) {
        return Result.successData(userFacade.login(username, password, getRequest()));
    }

    @Nullable
    private HttpServletRequest getRequest() {
        return Objects.nonNull(getServletRequestAttributes())?getServletRequestAttributes().getRequest():null;
    }

    @Nullable
    private ServletRequestAttributes getServletRequestAttributes() {
       return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }
}
