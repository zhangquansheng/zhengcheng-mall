package com.zhengcheng.mall.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 收款单方法
 *
 * @author quansheng1.zhang
 * @since 2022/5/19 22:10
 */
@Getter
public enum PaymentMethodEnum {

    /** 订单支付 */
    ONLINE("online", "订单支付"),

    /** 线下支付 */
    OFFLINE("offline", "线下支付"),

    /** 预存款充值 */
    DEPOSIT("deposit", "预存款充值");

    @EnumValue
    private final String value;

    private final String name;

    PaymentMethodEnum(String value, String name) {
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
    public static PaymentMethodEnum getByValue(String value) {
        for (PaymentMethodEnum paymentMethodEnum : PaymentMethodEnum.values()) {
            if (value.equals(paymentMethodEnum.getValue())) {
                return paymentMethodEnum;
            }
        }
        return PaymentMethodEnum.ONLINE;
    }
}
