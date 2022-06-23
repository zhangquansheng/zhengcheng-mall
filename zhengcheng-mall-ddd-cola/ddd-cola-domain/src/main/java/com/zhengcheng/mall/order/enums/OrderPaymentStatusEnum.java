package com.zhengcheng.mall.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 订单支付状态
 *
 * @author quansheng1.zhang
 * @since 2022/5/8 17:53
 */
@Getter
public enum OrderPaymentStatusEnum {

    UNPAID("未支付", "unpaid"),

    PARTIAL_PAYMENT("部分支付", "partialPayment"),

    PAID("已支付", "paid"),

    PARTIAL_REFUNDS("部分退款", "partialRefunds"),

    REFUNDED("已退款", "refunded");

    private final String name;

    @EnumValue
    private final String value;

    OrderPaymentStatusEnum(final String name, final String value) {
        this.name = name;
        this.value = value;
    }
}
