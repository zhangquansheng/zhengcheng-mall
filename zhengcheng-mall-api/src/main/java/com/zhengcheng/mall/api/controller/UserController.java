package com.zhengcheng.mall.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.UserCommand;
import com.zhengcheng.mall.api.controller.facade.UserFacade;
import com.zhengcheng.mall.api.dto.UserDTO;
import com.zhengcheng.mall.api.feign.UserFeignClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户(User)控制层
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:50
 */
@Api(tags = { "用户(User)接口" })
@RestController
@RequestMapping("/user")
public class UserController implements UserFeignClient {

    @Autowired
    private UserFacade userFacade;

    @ApiOperation("通过token获取用户消息")
    @GetMapping("/findByByToken")
    @Override
    public Result<UserDTO> findByByToken(@RequestParam("token") String token) {
        return Result.successData(userFacade.findByByToken(token));
    }

    @ApiOperation("根据用户名查询用户基本信息")
    @GetMapping("/findByUsername")
    @Override
    public Result<UserDTO> findByUsername(@RequestParam("username") String username) {
        return Result.successData(userFacade.findByUsername(username));
    }

    @ApiOperation("添加用户")
    @PostMapping("/add")
    @Override
    public Result<Long> add(@Validated @RequestBody UserCommand userCommand) {
        return Result.successData(userFacade.add(userCommand));
    }
}
