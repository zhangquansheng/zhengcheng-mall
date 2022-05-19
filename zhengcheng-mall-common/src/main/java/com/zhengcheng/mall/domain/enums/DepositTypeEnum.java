package com.zhengcheng.mall.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 预存款类型
 *
 * @author quansheng1.zhang
 * @since 2022/5/19 22:10
 */
@Getter
public enum DepositTypeEnum {
    /** 会员充值 */
    MEMBER_RECHARGE("memberRecharge", "会员充值"),

    /** 会员支付 */
    MEMBER_PAYMENT("memberPayment", "会员支付"),

    /** 后台充值 */
    ADMIN_RECHARGE("adminRecharge", "后台充值"),

    /** 后台扣费 */
    ADMIN_CHARGEBACK("adminChargeback", "后台扣费"),

    /** 后台支付 */
    ADMIN_PAYMENT("adminPayment", "后台支付"),

    /** 后台退款 */
    ADMIN_REFUNDS("adminRefunds", "后台退款");

    @EnumValue
    private final String value;

    private final String desc;

    DepositTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value获取类型
     *
     * @param value
     *            值
     * @return 枚举
     */
    public static DepositTypeEnum getByValue(Integer value) {
        for (DepositTypeEnum depositTypeEnum : DepositTypeEnum.values()) {
            if (value.equals(depositTypeEnum.getValue())) {
                return depositTypeEnum;
            }
        }
        return DepositTypeEnum.memberRecharge;
    }
}
