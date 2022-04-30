package com.zhengcheng.mall.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.common.interceptor.LoginInterceptor;
import com.zhengcheng.mall.admin.controller.command.LoginSubmitCommand;
import com.zhengcheng.mall.api.dto.TokenInfoDTO;
import com.zhengcheng.mall.api.feign.OauthFeignClient;

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
    private OauthFeignClient oauthFeign;

    @ApiOperation("登录页面")
    @RequestMapping
    public String login(String redirectUrl, ModelMap model, HttpSession session) {
        TokenInfoDTO tokenInfoDTO = (TokenInfoDTO) session.getAttribute(LoginInterceptor.PRINCIPAL_ATTRIBUTE_NAME);
        if (tokenInfoDTO != null) {
            return "redirect:/";
        }
        model.addAttribute("redirectUrl", StrUtil.isEmpty(redirectUrl) ? "/" : redirectUrl);
        model.addAttribute("publicKeyStr",
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2Nq+V4OqVHlIMxj7EVRW3Ofwm7E8sNf8rqmFoTpwvFnFwveKhsowZBjmH4Om9a7aQ6QqaOOMHe2URfhy5HuxhUIyq6Z6y3qF7i31wtbdCIEbmOobuW5oiHNF2AUQXQ752XrasEiuGom4JG1hgVIFAF68YIxeYzNgN8/I8AfxhsQIDAQAB");
        return "login";
    }

    //    @LogRecord(success = "{{#loginSubmitCommand.username}}登录系统,登录结果:{{#_ret}}", type = LogRecordType.USER, bizNo = "{{#loginSubmitCommand.username}}")
    @ApiOperation("登录")
    @PostMapping(value = "/submit")
    public @ResponseBody Result<TokenInfoDTO> submit(@RequestBody LoginSubmitCommand loginSubmitCommand,
                                                     HttpSession session) {
        Result<TokenInfoDTO> result = oauthFeign.postToken(loginSubmitCommand.getUsername(),
                loginSubmitCommand.getEnPassword());
        if (result.hasData()) {
            session.setAttribute(LoginInterceptor.PRINCIPAL_ATTRIBUTE_NAME, result.getData());
        }
        return result;
    }

}
