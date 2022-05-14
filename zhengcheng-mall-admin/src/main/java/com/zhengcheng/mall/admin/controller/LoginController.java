package com.zhengcheng.mall.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.common.dto.UserDTO;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.LoginSubmitCommand;
import com.zhengcheng.mall.admin.controller.facade.UserFacade;
import com.zhengcheng.mall.api.dto.TokenInfoDTO;
import com.zhengcheng.mall.common.interceptor.LoginInterceptor;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户登录
 *
 * @author quansheng.zhang
 * @since 2019-12-20
 */
@Api(tags = { "用户登录" })
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserFacade userFacade;

    @Value("${user.password.public-key}")
    private String     publicKeyBase64;

    @ApiOperation("登录页面")
    @RequestMapping
    public String login(String redirectUrl, ModelMap model, HttpSession session) {
        UserDTO userInfo = (UserDTO) session.getAttribute(LoginInterceptor.PRINCIPAL_ATTRIBUTE_NAME);
        if (userInfo != null) {
            return "redirect:/";
        }
        model.addAttribute("redirectUrl", StrUtil.isEmpty(redirectUrl) ? "/" : redirectUrl);
        model.addAttribute("publicKeyStr", publicKeyBase64);
        return "login";
    }

    @ApiOperation("登录")
    @PostMapping(value = "/submit")
    public @ResponseBody Result<TokenInfoDTO> submit(@RequestBody LoginSubmitCommand loginSubmitCommand,
                                                     HttpSession session, HttpServletRequest request) {
        return userFacade.login(loginSubmitCommand, session, request);
    }

}
