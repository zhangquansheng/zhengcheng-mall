package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.dto.ProductSpuDTO;
import com.zhengcheng.mall.admin.controller.facade.ProductSpuFacade;

import cn.dev33.satoken.annotation.SaCheckPermission;
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

    @ApiOperation("添加sku页面")
    @GetMapping("/addSku")
    public String add(Long spuId, Model model) {
        model.addAttribute("spu", productSpuFacade.findById(spuId));
        return "/view/product/product/addSku";
    }

    @ApiOperation("编辑页面")
    @GetMapping("/edit")
    public String edit(Model model) {
        return "/view/product/product/edit";
    }

    @Autowired
    private ProductSpuFacade productSpuFacade;

    @ApiOperation("分页查询")
    @SaCheckPermission("sys:product:main")
    @PostMapping("/page")
    public @ResponseBody Result<PageInfo<ProductSpuDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(productSpuFacade.page(pageCommand));
    }

    @ApiOperation("查询spu的sku数据")
    @GetMapping("/skuData")
    public @ResponseBody Result<JSONObject> skuData(@RequestParam("spuId") Long spuId) {
        return Result.successData(productSpuFacade.skuData(spuId));
    }

    @ApiOperation("保存sku数据")
    @PostMapping("/saveSkuData/{spuId}")
    public @ResponseBody Result<Void> skuData(@PathVariable("spuId") Long spuId, @RequestBody JSONObject skuData) {
        productSpuFacade.saveSkuData(spuId, skuData);
        return Result.success();
    }
}
