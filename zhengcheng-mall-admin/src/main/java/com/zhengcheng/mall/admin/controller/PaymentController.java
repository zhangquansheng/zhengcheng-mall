package com.zhengcheng.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.common.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 收款单
 *
 * @author quansheng1.zhang
 * @since 2022/5/14 16:48
 */
@Api(tags = { "收款单" })
@Controller
@RequestMapping("/payment")
public class PaymentController {

    @ApiOperation("提交")
    @PostMapping("/submit")
    public @ResponseBody Result<Void> submit() {
        return Result.errorMessage("下单失败，请稍后重试！");
    }

}
