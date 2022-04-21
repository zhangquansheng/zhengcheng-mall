package com.zhengcheng.mall.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.validation.annotation.Insert;
import com.zhengcheng.common.validation.annotation.Update;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.RoleAuthorityCommand;
import com.zhengcheng.mall.api.command.RoleCommand;
import com.zhengcheng.mall.api.controller.facade.RoleFacade;
import com.zhengcheng.mall.api.dto.RoleDTO;
import com.zhengcheng.mall.api.feign.RoleFeignClient;

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
public class RoleController implements RoleFeignClient {

    @Autowired
    private RoleFacade roleFacade;

    @Override
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("/{id}")
    public Result<RoleDTO> findById(@PathVariable("id") Long id) {
        return Result.successData(roleFacade.findById(id));
    }

    @Override
    @ApiOperation("添加单条数据")
    @PostMapping("/add")
    public Result<Long> add(@Validated({Insert.class}) @RequestBody RoleCommand roleCommand) {
        return Result.successData(roleFacade.add(roleCommand));
    }

    @Override
    @ApiOperation("更新单条数据")
    @PostMapping("/update")
    public Result<Long> update(@Validated({Update.class}) @RequestBody RoleCommand roleCommand) {
        return Result.successData(roleFacade.update(roleCommand));
    }

    @Override
    @ApiOperation("分页查询")
    @PostMapping("/page")
    public Result<PageInfo<RoleDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(roleFacade.page(pageCommand));
    }

    @Override
    @ApiOperation("编辑角色权限")
    @PostMapping("/authority")
    public Result<Void> authority(@Validated({Insert.class}) @RequestBody RoleAuthorityCommand roleAuthorityCommand) {
        roleFacade.addRoleAuthority(roleAuthorityCommand);
        return Result.success();
    }

}
