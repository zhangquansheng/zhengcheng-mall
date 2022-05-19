package com.zhengcheng.mall.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 收款单状态
 *
 * @author quansheng1.zhang
 * @since 2022/5/19 22:10
 */
@Getter
public enum PaymentStautsEnum {

    /** 等待支付 */
    WAIT("wait", "等待支付"),

    /** 支付成功 */
    SUCCESS("success", "支付成功"),

    /** 支付失败 */
    FAILURE("failure", "支付失败");

    @EnumValue
    private final String value;

    private final String name;

    PaymentStautsEnum(String value, String name) {
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
    public static PaymentStautsEnum getByValue(String value) {
        for (PaymentStautsEnum paymentStautsEnum : PaymentStautsEnum.values()) {
            if (value.equals(paymentStautsEnum.getValue())) {
                return paymentStautsEnum;
            }
        }
        return PaymentStautsEnum.WAIT;
    }
}
