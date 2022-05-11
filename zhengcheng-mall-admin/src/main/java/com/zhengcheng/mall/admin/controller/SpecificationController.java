package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.holder.ZcUserInfoHolder;
import com.zhengcheng.common.validation.annotation.Insert;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.SpecificationCommand;
import com.zhengcheng.mall.admin.controller.command.SpecificationRemoveCommand;
import com.zhengcheng.mall.admin.controller.dto.AttrSpecDTO;
import com.zhengcheng.mall.admin.controller.dto.SpecificationDTO;
import com.zhengcheng.mall.admin.controller.facade.SpecificationFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * SpecificationController
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 14:24
 */
@Api(tags = { "商品规格接口" })
@Controller
@RequestMapping("/admin/specification")
public class SpecificationController {

    @Autowired
    private SpecificationFacade specificationFacade;

    @ApiOperation("保存商品规格")
    @PostMapping("/save")
    public @ResponseBody Result<SpecificationDTO> save(@Validated(value = Insert.class) @RequestBody SpecificationCommand specificationCommand) {
        specificationCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(specificationFacade.add(specificationCommand));
    }

    @ApiOperation("删除商品规格")
    @PostMapping("/remove")
    public @ResponseBody Result<Boolean> removeById(@Valid @RequestBody SpecificationRemoveCommand specificationRemoveCommand) {
        return Result.successData(specificationFacade.removeById(specificationRemoveCommand.getId()));
    }

    @ApiOperation("查询属性规格")
    @GetMapping("/findAttrSpec")
    public @ResponseBody Result<AttrSpecDTO> findAttrSpec(@RequestParam("productCategoryId") Long productCategoryId) {
        return Result.successData(specificationFacade.findAttrSpec(productCategoryId));
    }
}
