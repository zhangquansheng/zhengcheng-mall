package com.zhengcheng.mall.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.controller.facade.UserLoginLogFacade;
import com.zhengcheng.mall.api.controller.facade.internal.dto.UserLoginLogDTO;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 登录日志表(UserLoginLog)控制层
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 19:51:46
 */
@Api(tags = {"登录日志表(UserLoginLog)接口"})
@SaCheckRole("admin")
@RestController
@RequestMapping("/userLoginLog")
public class UserLoginLogController {

    @Autowired
    private UserLoginLogFacade userLoginLogFacade;

    @ApiOperation("分页查询")
    @SaCheckPermission("user-login-log")
    @PostMapping("/page")
    public Result<PageInfo<UserLoginLogDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(userLoginLogFacade.page(pageCommand));
    }

}