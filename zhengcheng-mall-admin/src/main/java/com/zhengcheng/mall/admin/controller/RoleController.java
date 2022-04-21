package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.dto.RoleDTO;
import com.zhengcheng.mall.admin.controller.facade.RoleFacade;

import cn.dev33.satoken.annotation.SaCheckPermission;
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
    private RoleFacade roleFacade;

    @ApiOperation("角色页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/role";
    }

    @ApiOperation("分页查询")
    @SaCheckPermission("sys:role:main")
    @PostMapping("/page")
    public @ResponseBody
    Result<PageInfo<RoleDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(roleFacade.page(pageCommand));
    }
}
