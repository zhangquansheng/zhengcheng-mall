package com.zhengcheng.mall.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hutool.core.util.StrUtil;
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
        model.addAttribute("redirectUrl", StrUtil.isEmpty(redirectUrl)?"/":redirectUrl);
        model.addAttribute("publicKeyStr", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2Nq+V4OqVHlIMxj7EVRW3Ofwm7E8sNf8rqmFoTpwvFnFwveKhsowZBjmH4Om9a7aQ6QqaOOMHe2URfhy5HuxhUIyq6Z6y3qF7i31wtbdCIEbmOobuW5oiHNF2AUQXQ752XrasEiuGom4JG1hgVIFAF68YIxeYzNgN8/I8AfxhsQIDAQAB");
        return "login";
    }

}
