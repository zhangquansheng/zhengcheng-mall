package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.dto.RoleDTO;
import com.zhengcheng.mall.api.feign.RoleFeignClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * RoleController
 *
 * @author quansheng1.zhang
 * @since 2022/4/19 18:18
 */
@Api(tags = {"角色"})
@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleFeignClient roleFeignClient;

    @ApiOperation("分页查询")
    @PostMapping("/page")
    public Result<PageInfo<RoleDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return roleFeignClient.page(pageCommand);
    }
}