package com.zhengcheng.mall.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 订单状态
 *
 * @author quansheng1.zhang
 * @since 2022/5/8 17:53
 */
@Getter
public enum OrderStatusEnum {

    /** 未确认 */
    UNCONFIRMED("未确认", "unconfirmed"),

    /** 已确认 */
    CONFIRMED("已确认", "confirmed"),

    /** 已完成 */
    COMPLETED("已完成", "completed"),

    /** 已取消 */
    CANCELLED("已取消", "cancelled");

    private final String name;

    @EnumValue
    private final String value;

    OrderStatusEnum(final String name, final String value) {
        this.name = name;
        this.value = value;
    }
}
