package com.zhengcheng.mall.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.constant.CommonConstants;
import com.zhengcheng.common.validation.annotation.Insert;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.controller.command.UserCommand;
import com.zhengcheng.mall.api.controller.command.UserRoleCommand;
import com.zhengcheng.mall.api.controller.facade.UserFacade;
import com.zhengcheng.mall.api.controller.facade.internal.dto.UserDTO;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.URLUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户(User)控制层
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:50
 */
@Api(tags = {"用户(User)接口"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @ApiOperation("当前用户信息")
    @GetMapping("/current")
    public Result<UserDTO> current() {
        return Result.successData(userFacade.findCurrent(Long.parseLong(String.valueOf(StpUtil.getLoginId()))));
    }

    @ApiOperation("添加用户")
    @PostMapping("/add")
    public Result<Long> add(@RequestHeader(CommonConstants.USER_ID_PARAM_NAME) Long userId,
        @RequestHeader(CommonConstants.USER_NAME_PARAM_NAME) String userName,
        @Validated @RequestBody UserCommand userCommand) {
        userCommand.setUpdateUserId(userId);
        userCommand.setUpdateUserName(URLUtil.decode(userName));

        return Result.successData(userFacade.add(userCommand));
    }

    @ApiOperation("编辑用户角色")
    @PostMapping("/role")
    public Result<Void> role(@RequestHeader(CommonConstants.USER_ID_PARAM_NAME) Long userId,
        @RequestHeader(CommonConstants.USER_NAME_PARAM_NAME) String userName,
        @Validated({Insert.class}) @RequestBody UserRoleCommand userRoleCommand) {
        userRoleCommand.setUpdateUserId(userId);
        userRoleCommand.setUpdateUserName(URLUtil.decode(userName));
        userFacade.addUserRole(userRoleCommand);
        return Result.success();
    }

}