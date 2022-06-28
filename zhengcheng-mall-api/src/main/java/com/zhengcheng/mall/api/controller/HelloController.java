package com.zhengcheng.mall.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zhengcheng.common.web.Result;

/**
 * HelloController
 *
 * @author quansheng1.zhang
 * @since 2022/6/28 12:26
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @SentinelResource("resource")
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.successData("Hello");
    }

}
