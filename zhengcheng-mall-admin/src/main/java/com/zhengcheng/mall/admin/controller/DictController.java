package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.DictDataPageCommand;
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

    @ApiOperation("分页查询字典类型")
    @SaCheckPermission("sys:dict:main")
    @PostMapping("/type/page")
    public @ResponseBody Result<PageInfo<DictTypeDTO>> typePage(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(dictFacade.typePage(pageCommand));
    }

    @ApiOperation("分页查询字典")
    @SaCheckPermission("sys:dict:main")
    @PostMapping("/data/page")
    public @ResponseBody Result<PageInfo<DictDataDTO>> dataPage(@Valid @RequestBody DictDataPageCommand dictDataPageCommand) {
        return Result.successData(dictFacade.dataPage(dictDataPageCommand));
    }
}
