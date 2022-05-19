package com.zhengcheng.mall.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 收款单类型
 *
 * @author quansheng1.zhang
 * @since 2022/5/19 22:10
 */
@Getter
public enum PaymentTypeEnum {

    /** 订单支付 */
    PAYMENT("payment", "订单支付"),

    /** 预存款充值 */
    RECHARGE("recharge", "预存款充值");

    @EnumValue
    private final String value;

    private final String name;

    PaymentTypeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 根据value获取类型
     *
     * @param value
     *            值
     * @return 枚举
     */
    public static PaymentTypeEnum getByValue(String value) {
        for (PaymentTypeEnum paymentTypeEnum : PaymentTypeEnum.values()) {
            if (value.equals(paymentTypeEnum.getValue())) {
                return paymentTypeEnum;
            }
        }
        return PaymentTypeEnum.PAYMENT;
    }
}
