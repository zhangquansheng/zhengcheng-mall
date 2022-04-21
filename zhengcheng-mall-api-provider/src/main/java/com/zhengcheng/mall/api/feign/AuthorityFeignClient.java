package com.zhengcheng.mall.api.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zhengcheng.common.validation.annotation.Update;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.AuthorityCommand;
import com.zhengcheng.mall.api.dto.AuthorityDTO;
import com.zhengcheng.mall.api.dto.TreeNodeDTO;

import io.swagger.annotations.ApiOperation;

/**
 * AuthorityFeignClient
 *
 * @author quansheng1.zhang
 * @since 2022/4/21 9:40
 */
@FeignClient(name = AuthorityFeignClient.NAME, fallbackFactory = AuthorityFeignClientFallbackFactory.class)
public interface AuthorityFeignClient {
    /**
     * Nacos 对服务名大小写敏感
     */
    String NAME = "zhengcheng-mall";

    @ApiOperation("查询权限树")
    @GetMapping("/authority/tree")
    Result<List<TreeNodeDTO>> tree();

    @ApiOperation("查询所有权限数据")
    @GetMapping("/authority/data")
    Result<List<AuthorityDTO>> data();

    @ApiOperation("通过主键查询单条数据")
    @GetMapping("/authority/{id}")
    Result<AuthorityDTO> findById(@PathVariable("id") Long id);

    @ApiOperation("添加单条数据")
    @PostMapping("/authority/add")
    Result<Long> add(@Validated @RequestBody AuthorityCommand authorityCommand);

    @ApiOperation("更新单条数据")
    @PostMapping("/authority/update")
    Result<Long> update(@Validated({Update.class}) @RequestBody AuthorityCommand authorityCommand);
}
