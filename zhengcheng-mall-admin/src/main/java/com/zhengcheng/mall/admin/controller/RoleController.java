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
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.EnableCommand;
import com.zhengcheng.mall.admin.controller.command.RoleAuthorityCommand;
import com.zhengcheng.mall.admin.controller.command.RoleCommand;
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

    @ApiOperation("编辑角色页面")
    @GetMapping("/edit")
    public String edit(Long id, Model model) {
        model.addAttribute("role", roleFacade.findById(id));
        return "/view/system/role/edit";
    }

    @ApiOperation("授权页面")
    @GetMapping("/power")
    public String power(Long roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "/view/system/role/power";
    }

    @ApiOperation("分页查询")
    @SaCheckPermission("sys:role:main")
    @PostMapping("/page")
    public @ResponseBody Result<PageInfo<RoleDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(roleFacade.page(pageCommand));
    }

    @ApiOperation("根据ID删除")
    @SaCheckPermission("sys:role:del")
    @DeleteMapping("/remove/{id}")
    public @ResponseBody Result<Void> removeById(@PathVariable("id") Long id) {
        roleFacade.removeById(id);
        return Result.success();
    }

    @ApiOperation("根据ID列表批量删除")
    @SaCheckPermission("sys:role:del")
    @DeleteMapping("/batchRemove")
    public @ResponseBody Result<Boolean> batchRemove(@RequestParam("ids") List<Long> ids) {
        return Result.successData(roleFacade.batchRemove(ids));
    }

    @ApiOperation("根据ID启用/禁用")
    @PostMapping("/enable")
    public @ResponseBody Result<Boolean> enable(@Valid @RequestBody EnableCommand enableCommand) {
        enableCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(roleFacade.enable(enableCommand));
    }

    @ApiOperation("保存角色权限")
    @SaCheckPermission("sys:role:authority")
    @PostMapping("/saveRoleAuthority")
    public @ResponseBody Result<Void> saveRoleAuthority(@Valid @RequestBody RoleAuthorityCommand roleAuthorityCommand) {
        roleAuthorityCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        roleFacade.saveRoleAuthority(roleAuthorityCommand);
        return Result.success();
    }

    @ApiOperation("保存角色")
    @SaCheckPermission("sys:role:save")
    @PostMapping("/save")
    public @ResponseBody Result<Long> save(@Valid @RequestBody RoleCommand roleCommand) {
        roleCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(roleFacade.add(roleCommand));
    }

    @ApiOperation("更新角色")
    @SaCheckPermission("sys:role:update")
    @PostMapping("/update")
    public @ResponseBody Result<Long> update(@Validated(value = Update.class) @RequestBody RoleCommand roleCommand) {
        roleCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(roleFacade.update(roleCommand));
    }

}
