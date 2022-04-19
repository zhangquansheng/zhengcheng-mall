package com.zhengcheng.mall.api.feign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zhengcheng.common.validation.annotation.Insert;
import com.zhengcheng.common.validation.annotation.Update;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.RoleAuthorityCommand;
import com.zhengcheng.mall.api.command.RoleCommand;
import com.zhengcheng.mall.api.dto.RoleDTO;

import io.swagger.annotations.ApiOperation;

/**
 * RoleFeignClient
 *
 * @author quansheng1.zhang
 * @since 2022/3/9 18:41
 */
@FeignClient(name = RoleFeignClient.NAME, fallbackFactory = RoleFeignClientFallbackFactory.class)
public interface RoleFeignClient {
    /**
     * Nacos 对服务名大小写敏感
     */
    String NAME = "zhengcheng-mall";

    @ApiOperation("通过主键查询单条数据")
    @GetMapping("/role/{id}")
    Result<RoleDTO> findById(@PathVariable("id") Long id);

    @ApiOperation("添加单条数据")
    @PostMapping("/role/add")
    Result<Long> add(@Validated({Insert.class}) @RequestBody RoleCommand roleCommand);

    @ApiOperation("更新单条数据")
    @PostMapping("/role/update")
    Result<Long> update(@Validated({Update.class}) @RequestBody RoleCommand roleCommand);

    @ApiOperation("分页查询")
    @PostMapping("/role/page")
    Result<PageInfo<RoleDTO>> page(@Valid @RequestBody PageCommand pageCommand);

    @ApiOperation("编辑角色权限")
    @PostMapping("/role/authority")
    Result<Void> authority(@Validated({Insert.class}) @RequestBody RoleAuthorityCommand roleAuthorityCommand);
}
