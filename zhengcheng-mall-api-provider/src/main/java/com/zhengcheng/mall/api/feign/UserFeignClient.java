package com.zhengcheng.mall.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.UserCommand;
import com.zhengcheng.mall.api.dto.UserDTO;

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
    String NAME = "zhengcheng-mall";

    @ApiOperation("根据用户名查询用户基本信息")
    @GetMapping("/user/findByUsername")
    Result<UserDTO> findByUsername(@RequestParam("username")String username);

    @ApiOperation("当前用户信息")
    @GetMapping("/user/current")
    Result<UserDTO> current();

    @ApiOperation("添加用户")
    @PostMapping("/user/add")
    Result<Long> add(@Validated @RequestBody UserCommand userCommand);
}
