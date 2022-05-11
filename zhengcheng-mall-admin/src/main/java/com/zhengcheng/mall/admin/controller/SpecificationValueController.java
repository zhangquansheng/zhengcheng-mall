package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.common.holder.ZcUserInfoHolder;
import com.zhengcheng.common.validation.annotation.Insert;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.SpecificationValueCommand;
import com.zhengcheng.mall.admin.controller.command.SpecificationValueRemoveCommand;
import com.zhengcheng.mall.admin.controller.dto.SpecificationValueDTO;
import com.zhengcheng.mall.admin.controller.facade.SpecificationValueFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * SpecificationValueController
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 14:24
 */
@Api(tags = { "商品规格值接口" })
@Controller
@RequestMapping("/admin/specificationValue")
public class SpecificationValueController {

    @Autowired
    private SpecificationValueFacade specificationValueFacade;

    @ApiOperation("保存商品规格值")
    @PostMapping("/save")
    public @ResponseBody Result<SpecificationValueDTO> save(@Validated(value = Insert.class) @RequestBody SpecificationValueCommand specificationValueCommand) {
        specificationValueCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(specificationValueFacade.add(specificationValueCommand));
    }

    @ApiOperation("删除商品规格值")
    @PostMapping("/remove")
    public @ResponseBody Result<Boolean> removeById(@Valid @RequestBody SpecificationValueRemoveCommand specificationValueRemoveCommand) {
        return Result.successData(specificationValueFacade.removeById(specificationValueRemoveCommand.getId()));
    }

}
