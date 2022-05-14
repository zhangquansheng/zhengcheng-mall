package com.zhengcheng.mall.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.dto.UserDTO;
import com.zhengcheng.common.holder.ZcUserInfoHolder;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.EnableCommand;
import com.zhengcheng.mall.admin.controller.command.UserPageCommand;
import com.zhengcheng.mall.admin.controller.dto.MenuDTO;
import com.zhengcheng.mall.admin.controller.facade.RoleFacade;
import com.zhengcheng.mall.admin.controller.facade.UserFacade;
import com.zhengcheng.mall.api.command.UserCommand;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * UserController
 *
 * @author quansheng1.zhang
 * @since 2022/4/19 18:18
 */
@Api(tags = { "用户管理" })
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @ApiOperation("用户页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/user";
    }

    @ApiOperation("添加页面")
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("source", UserCommand.SOURCE_ADMIN);
        model.addAttribute("roles", roleFacade.findAll());
        return "/view/system/user/add";
    }

    @ApiOperation("编辑页面")
    @GetMapping("/edit")
    public String edit(Long id, Model model) {
        model.addAttribute("source", UserCommand.SOURCE_ADMIN);
        model.addAttribute("user", userFacade.findById(id));
        model.addAttribute("roles", roleFacade.findAll());
        return "/view/system/user/edit";
    }

    @ApiOperation("基本资料")
    @GetMapping("/person")
    public String person() {
        return "/view/system/person";
    }

    @Autowired
    private UserFacade userFacade;
    @Autowired
    private RoleFacade roleFacade;

    @ApiOperation("分页查询")
    @SaCheckPermission("sys:user:main")
    @PostMapping("/page")
    public @ResponseBody Result<PageInfo<UserDTO>> page(@Valid @RequestBody UserPageCommand userPageCommand) {
        return Result.successData(userFacade.page(userPageCommand));
    }

    @ApiOperation("用户后台管理菜单")
    @GetMapping("/menu")
    public @ResponseBody List<MenuDTO> menu() {
        return userFacade.menu(ZcUserInfoHolder.getUserId());
    }

    @ApiOperation("保存用户")
    @SaCheckPermission("sys:user:save")
    @PostMapping("/save")
    public @ResponseBody Result<Long> save(@Valid @RequestBody UserCommand userCommand) {
        userCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return userFacade.save(userCommand);
    }

    @ApiOperation("更新用户")
    @SaCheckPermission("sys:user:update")
    @PostMapping("/update")
    public @ResponseBody Result<Void> update(@Valid @RequestBody UserCommand userCommand) {
        userCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return userFacade.update(userCommand);
    }

    @ApiOperation("启用/禁用用户")
    @SaCheckPermission("sys:user:update")
    @PostMapping("/enable")
    public @ResponseBody Result<Void> enable(@Valid @RequestBody EnableCommand enableCommand) {
        enableCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        return userFacade.enable(enableCommand);
    }
}
