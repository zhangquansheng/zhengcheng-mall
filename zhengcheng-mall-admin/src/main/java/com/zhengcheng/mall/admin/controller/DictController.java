package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.dto.DictTypeDTO;
import com.zhengcheng.mall.admin.controller.facade.DictTypeFacade;

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
    private DictTypeFacade dictTypeFacade;

    @ApiOperation("字典页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/dict";
    }

    @ApiOperation("分页查询")
    @SaCheckPermission("sys:dictType:main")
    @PostMapping("/type/page")
    public @ResponseBody Result<PageInfo<DictTypeDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(dictTypeFacade.page(pageCommand));
    }
}
