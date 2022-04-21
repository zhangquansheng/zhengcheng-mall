package com.zhengcheng.mall.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.validation.annotation.Update;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.AuthorityCommand;
import com.zhengcheng.mall.api.controller.facade.AuthorityFacade;
import com.zhengcheng.mall.api.dto.AuthorityDTO;
import com.zhengcheng.mall.api.dto.TreeNodeDTO;
import com.zhengcheng.mall.api.feign.AuthorityFeignClient;

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
public class AuthorityController implements AuthorityFeignClient {

    @Autowired
    private AuthorityFacade authorityFacade;

    @Override
    @ApiOperation("查询权限树")
    @GetMapping("/tree")
    public Result<List<TreeNodeDTO>> tree() {
        return Result.successData(authorityFacade.findTree());
    }

    @Override
    @ApiOperation("查询所有权限数据")
    @GetMapping("/data")
    public Result<List<AuthorityDTO>> data() {
        return Result.successData(authorityFacade.findAll());
    }

    @Override
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("/{id}")
    public Result<AuthorityDTO> findById(@PathVariable("id") Long id) {
        return Result.successData(authorityFacade.findById(id));
    }

    @Override
    @ApiOperation("添加单条数据")
    @PostMapping("/add")
    public Result<Long> add(@Validated @RequestBody AuthorityCommand authorityCommand) {
        return Result.successData(authorityFacade.add(authorityCommand));
    }

    @Override
    @ApiOperation("更新单条数据")
    @PostMapping("/update")
    public Result<Long> update(@Validated({Update.class}) @RequestBody AuthorityCommand authorityCommand) {
        return Result.successData(authorityFacade.update(authorityCommand));
    }

}
