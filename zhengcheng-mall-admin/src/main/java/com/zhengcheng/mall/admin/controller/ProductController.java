package com.zhengcheng.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ProductController
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 11:17
 */
@Api(tags = { "商品管理" })
@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @ApiOperation("商品管理")
    @GetMapping("/view")
    public String view() {
        return "/view/product/product";
    }

    @ApiOperation("添加页面")
    @GetMapping("/add")
    public String add(Model model) {
        return "/view/product/product/add";
    }

    @ApiOperation("编辑页面")
    @GetMapping("/edit")
    public String edit(Model model) {
        return "/view/product/product/edit";
    }
}
