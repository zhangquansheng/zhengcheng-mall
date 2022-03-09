package com.zhengcheng.mall.api.controller;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhengcheng.common.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * JasyptController
 *
 * @author quansheng1.zhang
 * @since 2022/3/9 16:41
 */
@Api(tags = {"jasypt-spring-boot-starter加密配置接口"})
@RestController
@RequestMapping("/jasypt")
public class JasyptController {

    @ApiOperation("获取加密后内容")
    @GetMapping("/basicTextEncryptor")
    public Result<String> basicTextEncryptor(@RequestParam("password")String password,@RequestParam("message") String message) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password);
        return Result.successData(textEncryptor.encrypt(message));
    }
}
