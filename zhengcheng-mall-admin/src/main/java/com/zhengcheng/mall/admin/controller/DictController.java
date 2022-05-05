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
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.*;
import com.zhengcheng.mall.admin.controller.dto.DictDataDTO;
import com.zhengcheng.mall.admin.controller.dto.DictTypeDTO;
import com.zhengcheng.mall.admin.controller.facade.DictFacade;

import cn.dev33.satoken.annotation.SaCheckPermission;
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

    @Autowired
    private DictFacade dictFacade;

    @ApiOperation("字典页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/dict";
    }

    @ApiOperation("添加字典数据")
    @GetMapping("/addData")
    public String addData(Model model) {
        model.addAttribute("dictTypes", dictFacade.typeList());
        return "/view/system/dict/addData";
    }

    @ApiOperation("编辑字典数据")
    @GetMapping("/editData")
    public String editData(Long id, Model model) {
        model.addAttribute("dictTypes", dictFacade.typeList());
        model.addAttribute("dictData", dictFacade.findDataById(id));
        return "/view/system/dict/editData";
    }

    @ApiOperation("添加字典类型")
    @GetMapping("/addType")
    public String addType() {
        return "/view/system/dict/addType";
    }

    @ApiOperation("编辑字典类型")
    @GetMapping("/editType")
    public String editType(Long id, Model model) {
        model.addAttribute("dictType", dictFacade.findTypeById(id));
        return "/view/system/dict/editType";
    }

    @ApiOperation("分页查询字典类型")
    @SaCheckPermission("sys:dict:main")
    @PostMapping("/type/page")
    public @ResponseBody Result<PageInfo<DictTypeDTO>> typePage(@Valid @RequestBody DictTypePageCommand pageCommand) {
        return Result.successData(dictFacade.typePage(pageCommand));
    }

    @ApiOperation("分页查询字典")
    @SaCheckPermission("sys:dict:main")
    @PostMapping("/data/page")
    public @ResponseBody Result<PageInfo<DictDataDTO>> dataPage(@Valid @RequestBody DictDataPageCommand dictDataPageCommand) {
        return Result.successData(dictFacade.dataPage(dictDataPageCommand));
    }

    @ApiOperation("保存字典数据")
    @PostMapping("/save/data")
    public @ResponseBody Result<Boolean> addData(@Valid @RequestBody DictDataCommand dictDataCommand) {
        dictDataCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(dictFacade.addData(dictDataCommand));
    }

    @ApiOperation("更新字典数据")
    @PostMapping("/update/data")
    public @ResponseBody Result<Boolean> updateData(@Validated(Update.class) @RequestBody DictDataCommand dictDataCommand) {
        dictDataCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(dictFacade.updateData(dictDataCommand));
    }

    @ApiOperation("删除字典数据")
    @SaCheckPermission("sys:dict:del")
    @DeleteMapping("/remove/data/{id}")
    public @ResponseBody Result<Boolean> removeData(@PathVariable("id") Long id) {
        return Result.successData(dictFacade.removeData(id));
    }

    @ApiOperation("批量删除字典数据")
    @SaCheckPermission("sys:dict:del")
    @DeleteMapping("/batchRemove/data")
    public @ResponseBody Result<Boolean> batchRemoveData(@RequestParam("ids") List<Long> ids) {
        return Result.successData(dictFacade.batchRemoveData(ids));
    }

    @ApiOperation("根据ID启用/禁用字典数据")
    @PostMapping("/enable/data")
    public @ResponseBody Result<Boolean> enableData(@Valid @RequestBody EnableCommand enableCommand) {
        enableCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(dictFacade.enableData(enableCommand));
    }

    @ApiOperation("保存字典类型")
    @PostMapping("/save/type")
    public @ResponseBody Result<Boolean> saveType(@Valid @RequestBody DictTypeCommand dictTypeCommand) {
        dictTypeCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(dictFacade.saveType(dictTypeCommand));
    }

    @ApiOperation("删除字典类型")
    @SaCheckPermission("sys:dict:del")
    @DeleteMapping("/remove/type/{id}")
    public @ResponseBody Result<Boolean> removeType(@PathVariable("id") Long id) {
        return Result.successData(dictFacade.removeType(id));
    }

    @ApiOperation("更新字典数据类型")
    @PostMapping("/update/type")
    public @ResponseBody Result<Boolean> updateType(@Validated(Update.class) @RequestBody DictTypeCommand dictTypeCommand) {
        dictTypeCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(dictFacade.updateType(dictTypeCommand));
    }

    @ApiOperation("根据ID启用/禁用字典数据类型")
    @PostMapping("/enable/type")
    public @ResponseBody Result<Boolean> enableType(@Valid @RequestBody EnableCommand enableCommand) {
        enableCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(dictFacade.enableType(enableCommand));
    }

    @ApiOperation("批量删除字典数据类型")
    @SaCheckPermission("sys:dict:del")
    @DeleteMapping("/batchRemove/type")
    public @ResponseBody Result<Boolean> batchRemoveType(@RequestParam("ids") List<Long> ids) {
        return Result.successData(dictFacade.batchRemoveType(ids));
    }
}
