package com.zhengcheng.mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.dto.AuthorityDTO;
import com.zhengcheng.mall.admin.controller.facade.AuthorityFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * AuthorityController
 *
 * @author quansheng1.zhang
 * @since 2022/4/21 9:27
 */
@Api(tags = { "权限" })
@Controller
@RequestMapping("/admin/authority")
public class AuthorityController {

    @ApiOperation("权限页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/authority";
    }

    @Autowired
    private AuthorityFacade authorityFacade;

    @ApiOperation("查询所有权限数据")
    @GetMapping("/data")
    public @ResponseBody Result<List<AuthorityDTO>> data() {
        return Result.successData(authorityFacade.findAll());
    }

}
