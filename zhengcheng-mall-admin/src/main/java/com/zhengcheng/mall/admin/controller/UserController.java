package com.zhengcheng.mall.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.UserPageCommand;
import com.zhengcheng.mall.admin.controller.dto.MenuDTO;
import com.zhengcheng.mall.admin.controller.facade.UserFacade;
import com.zhengcheng.mall.api.dto.UserDTO;
import com.zhengcheng.mall.common.holder.TokenInfoHolder;

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

    @Autowired
    private UserFacade userFacade;

    @ApiOperation("分页查询")
    @SaCheckPermission("sys:user:main")
    @PostMapping("/page")
    public @ResponseBody Result<PageInfo<UserDTO>> page(@Valid @RequestBody UserPageCommand userPageCommand) {
        return Result.successData(userFacade.page(userPageCommand));
    }

    @ApiOperation("用户后台管理菜单")
    @GetMapping("/menu")
    public @ResponseBody List<MenuDTO> menu() {
        return userFacade.menu(TokenInfoHolder.getUserId());
    }
}
