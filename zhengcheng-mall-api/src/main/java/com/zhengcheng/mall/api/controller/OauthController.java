package com.zhengcheng.mall.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.controller.facade.OauthFacade;

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
public class OauthController {

    @Autowired
    private OauthFacade userFacade;

    @ApiOperation(value = "注销登录")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result<Void> logout() {
        StpUtil.logout();
        return Result.success();
    }

    @ApiOperation(value = "指定token的会话注销登录")
    @RequestMapping(value = "/logoutByToken", method = RequestMethod.GET)
    public Result<Void> logoutByToken(@RequestParam("access_token") String accessToken) {
        StpUtil.logoutByTokenValue(accessToken);
        return Result.success();
    }

    @ApiOperation(value = "password获取token")
    @GetMapping("/token")
    public Result<SaTokenInfo> getToken(@RequestParam("username") String username,
        @RequestParam("enPassword") String enPassword, HttpServletRequest request) {
        return Result.successData(userFacade.login(username, enPassword, request));
    }

    @ApiOperation(value = "password获取token")
    @PostMapping("/token")
    public Result<SaTokenInfo> postToken(@RequestParam("username") String username,
        @RequestParam("password") String password, HttpServletRequest request) {
        return Result.successData(userFacade.login(username, password, request));
    }

}
