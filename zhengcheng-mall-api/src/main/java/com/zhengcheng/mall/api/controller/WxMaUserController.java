package com.zhengcheng.mall.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.controller.facade.OauthFacade;

import cn.dev33.satoken.stp.SaTokenInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 微信小程序用户接口
 *
 * @author quansheng1.zhang
 * @since 2022/6/28 15:33
 */
@Api(tags = { "微信小程序用户接口" })
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxMaUserController {

    @Autowired
    private OauthFacade userFacade;

    @ApiOperation("登陆接口")
    @GetMapping("/login")
    public Result<SaTokenInfo> login(@PathVariable String appid, String code, HttpServletRequest request) {
        return Result.successData(userFacade.wxMaLogin(appid, code, request));
    }

}
