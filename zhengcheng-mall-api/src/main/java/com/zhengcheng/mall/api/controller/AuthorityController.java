package com.zhengcheng.mall.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.constant.CommonConstants;
import com.zhengcheng.common.validation.annotation.Update;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.controller.command.AuthorityCommand;
import com.zhengcheng.mall.api.controller.facade.AuthorityFacade;
import com.zhengcheng.mall.api.controller.facade.internal.dto.AuthorityDTO;
import com.zhengcheng.mall.api.controller.facade.internal.dto.TreeNodeDTO;

import cn.hutool.core.util.URLUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 权限表(Authority)控制层
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:46:58
 */
@Api(tags = {"权限表(Authority)接口"})
@RestController
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private AuthorityFacade authorityFacade;

    @ApiOperation("查询权限树")
    @GetMapping("/tree")
    public Result<List<TreeNodeDTO>> tree() {
        return Result.successData(authorityFacade.findTree());
    }

    @ApiOperation("通过主键查询单条数据")
    @GetMapping("/{id}")
    public Result<AuthorityDTO> findById(@PathVariable("id") Long id) {
        return Result.successData(authorityFacade.findById(id));
    }

    @ApiOperation("添加单条数据")
    @PostMapping("/add")
    public Result<Long> add(@RequestHeader(CommonConstants.USER_ID_PARAM_NAME) Long userId,
        @RequestHeader(CommonConstants.USER_NAME_PARAM_NAME) String userName,
        @Validated @RequestBody AuthorityCommand authorityCommand) {
        authorityCommand.setUpdateUserId(userId);
        authorityCommand.setUpdateUserName(URLUtil.decode(userName));

        return Result.successData(authorityFacade.add(authorityCommand));
    }

    @ApiOperation("更新单条数据")
    @PostMapping("/update")
    public Result<Long> update(@RequestHeader(CommonConstants.USER_ID_PARAM_NAME) Long userId,
        @RequestHeader(CommonConstants.USER_NAME_PARAM_NAME) String userName,
        @Validated({Update.class}) @RequestBody AuthorityCommand authorityCommand) {
        authorityCommand.setUpdateUserId(userId);
        authorityCommand.setUpdateUserName(URLUtil.decode(userName));

        return Result.successData(authorityFacade.update(authorityCommand));
    }

}
