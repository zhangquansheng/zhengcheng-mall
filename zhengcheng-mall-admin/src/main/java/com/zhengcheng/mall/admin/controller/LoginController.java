package com.zhengcheng.mall.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户登录
 *
 * @author quansheng.zhang
 * @since 2019-12-20
 */
@Api(tags = {"用户登录"})
@Controller
@RequestMapping("/login")
public class LoginController  {

    @ApiOperation("登录页面")
    @RequestMapping
    public String login(String redirectUrl, ModelMap model) {
//        User user = userService.getCurrent();
//        if (user != null) {
//            return "redirect:/";
//        }
        model.addAttribute("redirectUrl", redirectUrl);
        model.addAttribute("publicKeyStr", "");
        return "login";
    }

}
