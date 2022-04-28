package com.zhengcheng.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * DictController
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 18:38
 */
@Api(tags = { "字典管理" })
@Controller
@RequestMapping("/admin/dict")
public class DictController {

    @ApiOperation("字典页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/dict";
    }

}
