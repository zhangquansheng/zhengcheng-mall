package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.UserPageCommand;
import com.zhengcheng.mall.admin.controller.dto.UserDTO;
import com.zhengcheng.mall.admin.controller.facade.UserFacade;

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
}
