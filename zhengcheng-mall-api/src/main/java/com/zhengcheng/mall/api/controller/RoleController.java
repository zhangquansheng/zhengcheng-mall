package com.zhengcheng.mall.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.constant.CommonConstants;
import com.zhengcheng.common.validation.annotation.Insert;
import com.zhengcheng.common.validation.annotation.Update;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.controller.command.RoleAuthorityCommand;
import com.zhengcheng.mall.api.controller.command.RoleCommand;
import com.zhengcheng.mall.api.controller.facade.RoleFacade;
import com.zhengcheng.mall.api.controller.facade.internal.dto.RoleDTO;

import cn.hutool.core.util.URLUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色表(Role)控制层
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:19:03
 */
@Api(tags = {"角色表(Role)接口"})
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleFacade roleFacade;

    @ApiOperation("通过主键查询单条数据")
    @GetMapping("/{id}")
    public Result<RoleDTO> findById(@PathVariable("id") Long id) {
        return Result.successData(roleFacade.findById(id));
    }

    @ApiOperation("添加单条数据")
    @PostMapping("/add")
    public Result<Long> add(@RequestHeader(CommonConstants.USER_ID_PARAM_NAME) Long userId,
        @RequestHeader(CommonConstants.USER_NAME_PARAM_NAME) String userName,
        @Validated({Insert.class}) @RequestBody RoleCommand roleCommand) {
        roleCommand.setUpdateUserId(userId);
        roleCommand.setUpdateUserName(URLUtil.decode(userName));

        return Result.successData(roleFacade.add(roleCommand));
    }

    @ApiOperation("更新单条数据")
    @PostMapping("/update")
    public Result<Long> update(@RequestHeader(CommonConstants.USER_ID_PARAM_NAME) Long userId,
        @RequestHeader(CommonConstants.USER_NAME_PARAM_NAME) String userName,
        @Validated({Update.class}) @RequestBody RoleCommand roleCommand) {
        roleCommand.setUpdateUserId(userId);
        roleCommand.setUpdateUserName(URLUtil.decode(userName));

        return Result.successData(roleFacade.update(roleCommand));
    }

    @ApiOperation("分页查询")
    @PostMapping("/page")
    public Result<PageInfo<RoleDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(roleFacade.page(pageCommand));
    }

    @ApiOperation("编辑角色权限")
    @PostMapping("/authority")
    public Result<Void> authority(@RequestHeader(CommonConstants.USER_ID_PARAM_NAME) Long userId,
        @RequestHeader(CommonConstants.USER_NAME_PARAM_NAME) String userName,
        @Validated({Insert.class}) @RequestBody RoleAuthorityCommand roleAuthorityCommand) {
        roleAuthorityCommand.setUpdateUserId(userId);
        roleAuthorityCommand.setUpdateUserName(URLUtil.decode(userName));
        roleFacade.addRoleAuthority(roleAuthorityCommand);
        return Result.success();
    }

}
