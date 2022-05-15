package com.zhengcheng.mall.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.holder.ZcUserInfoHolder;
import com.zhengcheng.common.validation.annotation.Update;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.ProductCategoryCommand;
import com.zhengcheng.mall.admin.controller.dto.ProductCategoryDTO;
import com.zhengcheng.mall.admin.controller.facade.ProductCategoryFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ProductCategoryController
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 12:06
 */
@Api(tags = { "商品分类管理" })
@Controller
@RequestMapping("/admin/productCategory")
public class ProductCategoryController {

    @ApiOperation("商品管理")
    @GetMapping("/view")
    public String view() {
        return "/view/product/category";
    }

    @ApiOperation("添加商品")
    @GetMapping("/add")
    public String add() {
        return "/view/product/category/add";
    }

    @ApiOperation("编辑管理")
    @GetMapping("/edit")
    public String edit(Long id, Model model) {
        model.addAttribute("productCategory", productCategoryFacade.findById(id));
        return "/view/product/category/edit";
    }

    @Autowired
    private ProductCategoryFacade productCategoryFacade;

    @ApiOperation("查询所有商品分类数据")
    @GetMapping("/data")
    public @ResponseBody Result<List<ProductCategoryDTO>> data() {
        return Result.successData(productCategoryFacade.findAll());
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public @ResponseBody Result<Void> save(@Valid @RequestBody ProductCategoryCommand productCategoryCommand) {
        productCategoryCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        productCategoryFacade.save(productCategoryCommand);
        return Result.success();
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public @ResponseBody Result<Boolean> update(@Validated(Update.class) @RequestBody ProductCategoryCommand productCategoryCommand) {
        productCategoryCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(productCategoryFacade.update(productCategoryCommand));
    }

    @ApiOperation("删除")
    @DeleteMapping("/remove/{id}")
    public @ResponseBody Result<Boolean> remove(@PathVariable("id") Long id) {
        return Result.successData(productCategoryFacade.removeById(id));
    }
}
