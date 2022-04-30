package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.dto.LogRecordDTO;
import com.zhengcheng.mall.admin.controller.facade.LogRecordFacade;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LogRecordController
 *
 * @author quansheng1.zhang
 * @since 2022/4/30 21:23
 */
@Api(tags = { "日志管理" })
@Controller
@RequestMapping("/admin/log")
public class LogRecordController {

    @ApiOperation("日志页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/log";
    }

    @Autowired
    private LogRecordFacade logRecordFacade;

    @ApiOperation("分页查询")
    @SaCheckPermission("sys:log:main")
    @PostMapping("/page")
    public @ResponseBody Result<PageInfo<LogRecordDTO>> page(@Valid @RequestBody PageCommand pageCommand) {
        return Result.successData(logRecordFacade.page(pageCommand));
    }
}
