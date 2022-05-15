package com.zhengcheng.mall.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.kaptcha.Kaptcha;

/**
 * https://gitee.com/baomidou/kaptcha-spring-boot-starter
 * 
 * @author quansheng.zhang
 * @since 2022-05-15
 */
@RestController
@RequestMapping("/admin/kaptcha")
public class KaptchaController {

    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    @PostMapping("/valid")
    public void validDefaultTime(@RequestParam String code) {
        //default timeout 900 seconds
        kaptcha.validate(code);
    }

    @PostMapping("/validTime")
    public void validCustomTime(@RequestParam String code) {
        kaptcha.validate(code, 60);
    }

}
