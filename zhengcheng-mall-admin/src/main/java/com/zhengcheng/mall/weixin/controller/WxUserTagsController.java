package com.zhengcheng.mall.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信用户标签
 *
 * @author quansheng1.zhang
 * @since 2022/4/19 18:18
 */
@Slf4j
@Controller
@RequestMapping("/admin/wxusertags")
public class WxUserTagsController {

    @ApiOperation("微信用户标签页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/wxusertags";
    }

}
