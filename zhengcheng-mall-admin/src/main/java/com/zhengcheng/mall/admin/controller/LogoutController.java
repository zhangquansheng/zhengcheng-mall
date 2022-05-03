package com.zhengcheng.mall.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhengcheng.mall.common.interceptor.LoginInterceptor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 退出登录
 *
 * @author :    zhangquansheng
 * @date :    2020/1/9 17:18
 */
@Api(tags = { "退出登录" })
@Controller
@RequestMapping("/logout")
public class LogoutController {

    @ApiOperation("退出页面")
    @RequestMapping
    public String logout(HttpSession session) {
        session.removeAttribute(LoginInterceptor.PRINCIPAL_ATTRIBUTE_NAME);
        return "redirect:/";
    }

}
