package com.zhengcheng.mall.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhengcheng.common.dto.UserDTO;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.UserCommand;

import io.swagger.annotations.ApiOperation;

/**
 * OauthFeign
 *
 * @author quansheng1.zhang
 * @since 2022/3/9 18:41
 */
@FeignClient(name = UserFeignClient.NAME, fallbackFactory = UserFeignClientFallbackFactory.class)
public interface UserFeignClient {
    /**
     * Nacos 对服务名大小写敏感
     */
    String NAME = "zhengcheng-mall-api";

    @ApiOperation("通过token获取用户消息")
    @GetMapping("/user/findByByToken")
    Result<UserDTO> findByByToken(@RequestParam("token") String token);

    @ApiOperation("根据用户名查询用户基本信息")
    @GetMapping("/user/findByUsername")
    Result<UserDTO> findByUsername(@RequestParam("username") String username);

    @ApiOperation("添加用户")
    @PostMapping("/user/add")
    Result<Long> add(@Validated @RequestBody UserCommand userCommand);

    @ApiOperation("更新用户")
    @PostMapping("/user/update")
    Result<Void> update(@RequestBody UserCommand userCommand);
}
