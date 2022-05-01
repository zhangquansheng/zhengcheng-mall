package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.facade.UserLoginLogFacade;
import com.zhengcheng.mall.common.dto.UserLoginLogDTO;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * UserLoginLogController
 *
 * @author quansheng1.zhang
 * @since 2022/4/30 21:23
 */
@Api(tags = { "登录日志管理" })
@Controller
@RequestMapping("/admin/userLoginLog")
public class UserLoginLogController {

    @Autowired
    private UserLoginLogFacade userLoginLogFacade;

    @ApiOperation("分页查询")
    @SaCheckPermission("sys:log:main")
    @PostMapping("/page")
    public @ResponseBody Result<PageInfo<UserLoginLogDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(userLoginLogFacade.page(pageCommand));
    }
}
