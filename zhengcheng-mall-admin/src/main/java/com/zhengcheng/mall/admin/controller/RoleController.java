package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.holder.ZcUserInfoHolder;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.EnableCommand;
import com.zhengcheng.mall.admin.controller.dto.RoleDTO;
import com.zhengcheng.mall.admin.controller.facade.RoleFacade;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * RoleController
 *
 * @author quansheng1.zhang
 * @since 2022/4/19 18:18
 */
@Api(tags = { "角色管理" })
@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleFacade roleFacade;

    @ApiOperation("角色页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/role";
    }

    @ApiOperation("新增角色页面")
    @GetMapping("/add")
    public String add() {
        return "/view/system/role/add";
    }

    @ApiOperation("分页查询")
    @SaCheckPermission("sys:role:main")
    @PostMapping("/page")
    public @ResponseBody Result<PageInfo<RoleDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(roleFacade.page(pageCommand));
    }

    @ApiOperation("根据ID删除")
    @SaCheckPermission("sys:role:del")
    @DeleteMapping("/operate/remove/{id}")
    public @ResponseBody Result<Void> removeById(@PathVariable("id") Long id) {
        roleFacade.removeById(id);
        return Result.success();
    }

    @ApiOperation("根据ID启用/禁用")
    @PostMapping("/operate/enable")
    public @ResponseBody Result<Boolean> enable(@Valid @RequestBody EnableCommand enableCommand) {
        enableCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(roleFacade.enable(enableCommand));
    }
}
