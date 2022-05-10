package com.zhengcheng.mall.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * PayController
 *
 * @author quansheng1.zhang
 * @since 2022/5/10 12:55
 */
@Slf4j
@Controller
@RequestMapping("/admin/pay")
public class PayController {

    @RequestMapping("/wxpay")
    public String wxpay() {
        return "/view/pay/wxpay";
    }

}
