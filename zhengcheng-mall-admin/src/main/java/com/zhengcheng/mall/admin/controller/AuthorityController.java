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
import com.zhengcheng.mall.admin.controller.command.AuthorityCommand;
import com.zhengcheng.mall.admin.controller.command.EnableCommand;
import com.zhengcheng.mall.admin.controller.dto.AuthorityDTO;
import com.zhengcheng.mall.admin.controller.facade.AuthorityFacade;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * AuthorityController
 *
 * @author quansheng1.zhang
 * @since 2022/4/21 9:27
 */
@Api(tags = { "权限" })
@Controller
@RequestMapping("/admin/authority")
public class AuthorityController {

    @ApiOperation("权限页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/authority";
    }

    @ApiOperation("新增权限页面")
    @GetMapping("/operate/add")
    public String add() {
        return "/view/system/authority/add";
    }

    @ApiOperation("编辑权限页面")
    @GetMapping("/operate/edit")
    public String edit(Long id, Model model) {
        model.addAttribute("authority", authorityFacade.findById(id));
        return "/view/system/authority/edit";
    }

    @Autowired
    private AuthorityFacade authorityFacade;

    @ApiOperation("查询所有权限数据")
    @GetMapping("/data")
    public @ResponseBody Result<List<AuthorityDTO>> data(@RequestParam(value = "pid", required = false) Long pid) {
        return Result.successData(authorityFacade.findByPid(pid));
    }

    @ApiOperation("保存")
    @SaCheckPermission("sys:authority:save")
    @PostMapping("/save")
    public @ResponseBody Result<Void> save(@Valid @RequestBody AuthorityCommand authorityCommand) {
        authorityCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        authorityFacade.save(authorityCommand);
        return Result.success();
    }

    @ApiOperation("删除")
    @SaCheckPermission("sys:authority:del")
    @DeleteMapping("/operate/remove/{id}")
    public @ResponseBody Result<Boolean> remove(@PathVariable("id") Long id) {
        return Result.successData(authorityFacade.deleteById(id));
    }

    @ApiOperation("更新")
    @SaCheckPermission("sys:authority:update")
    @PostMapping("/update")
    public @ResponseBody Result<Long> update(@Validated(value = Update.class) @RequestBody AuthorityCommand authorityCommand) {
        authorityCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        authorityFacade.update(authorityCommand);
        return Result.success();
    }

    @ApiOperation("根据ID启用/禁用")
    @SaCheckPermission("sys:authority:enable")
    @PostMapping("/operate/enable")
    public @ResponseBody Result<Boolean> enable(@Valid @RequestBody EnableCommand enableCommand) {
        enableCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return Result.successData(authorityFacade.enable(enableCommand));
    }

    @ApiOperation("根据角色ID查询权限树数据")
    @GetMapping("/findByRoleId")
    public @ResponseBody Result<List<AuthorityDTO>> findByRoleId(@RequestParam(value = "roleId", required = false) Long roleId) {
        return Result.successData(authorityFacade.findByRoleId(roleId));
    }

}
