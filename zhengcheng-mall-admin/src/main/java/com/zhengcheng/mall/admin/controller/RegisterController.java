package com.zhengcheng.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户注册
 *
 * @author :    quansheng.zhang
 * @date :    2020/1/8 20:01
 */
@Api(tags = {"用户注册"})
@Controller
@RequestMapping("/reg")
public class RegisterController {

    @ApiOperation("注册页面")
    @GetMapping
    public String reg(ModelMap model) {
        model.addAttribute("publicKeyStr", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2Nq+V4OqVHlIMxj7EVRW3Ofwm7E8sNf8rqmFoTpwvFnFwveKhsowZBjmH4Om9a7aQ6QqaOOMHe2URfhy5HuxhUIyq6Z6y3qF7i31wtbdCIEbmOobuW5oiHNF2AUQXQ752XrasEiuGom4JG1hgVIFAF68YIxeYzNgN8/I8AfxhsQIDAQAB");
        return "reg";
    }

}
