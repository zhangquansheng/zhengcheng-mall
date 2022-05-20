package com.zhengcheng.mall.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.PaymentPageCommand;
import com.zhengcheng.mall.admin.controller.dto.PaymentDTO;
import com.zhengcheng.mall.admin.controller.facade.PaymentFacade;

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
@RequestMapping("/admin/payment")
public class PaymentController {

    @ApiOperation("权限页面")
    @GetMapping("/view")
    public String view() {
        return "/view/order/payment";
    }

    @Autowired
    private PaymentFacade paymentFacade;

    @ApiOperation("提交")
    @PostMapping("/submit")
    public @ResponseBody Result<Void> submit() {
        return Result.errorMessage("下单失败，请稍后重试！");
    }

    @ApiOperation("分页查询")
    @PostMapping("/page")
    public @ResponseBody Result<PageInfo<PaymentDTO>> page(@Valid @RequestBody PaymentPageCommand paymentPageCommand) {
        return Result.successData(paymentFacade.page(paymentPageCommand));
    }

}
