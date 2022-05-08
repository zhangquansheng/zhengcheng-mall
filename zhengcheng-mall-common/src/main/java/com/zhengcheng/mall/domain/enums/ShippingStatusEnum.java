package com.zhengcheng.mall.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 配送状态
 *
 * @author quansheng1.zhang
 * @since 2022/5/8 17:54
 */
@Getter
public enum ShippingStatusEnum {

    /** 未发货 */
    UNSHIPPED("未发货", "unshipped"),

    /** 部分发货 */
    PARTIAL_SHIPMENT("部分发货", "partialShipment"),

    /** 已发货 */
    SHIPPED("已发货", "shipped"),

    /** 部分退货 */
    PARTIAL_RETURNS("部分退货", "partialReturns"),

    /** 已退货 */
    RETURNED("已退货", "returned");

    private final String name;

    @EnumValue
    private final String value;

    ShippingStatusEnum(final String name, final String value) {
        this.name = name;
        this.value = value;
    }
}
