package com.zhengcheng.mall.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Order - 订单 
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 22:08
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_order")
public class Order extends BaseEntity<Order> {

    private static final long serialVersionUID = 4645306022711432759L;

    /** 订单编号 */
    private String            orderNo;

    /** 订单状态 */
    private String            orderStatus;

    /** 支付状态 */
    private String            paymentStatus;

    /** 配送状态 */
    private String            shippingStatus;

    /** 支付手续费 */
    private BigDecimal        fee;

    /** 运费 */
    private BigDecimal        freight;

    /** 促销折扣 */
    private BigDecimal        promotionDiscount;

    /** 优惠券折扣 */
    private BigDecimal        couponDiscount;

    /** 调整金额 */
    private BigDecimal        offsetAmount;

    /** 已付金额 */
    private BigDecimal        amountPaid;

    /** 赠送积分 */
    private Long              point;

    /** 收货人 */
    private String            consignee;

    /** 地区名称 */
    private String            areaName;

    /** 地址 */
    private String            address;

    /** 邮编 */
    private String            zipCode;

    /** 电话 */
    private String            phone;

    /** 是否开据发票 */
    @TableField(value = "is_invoice")
    private Boolean           invoice;

    /** 发票抬头 */
    private String            invoiceTitle;

    /** 税金 */
    private BigDecimal        tax;

    /** 附言 */
    private String            memo;

    /** 促销 */
    private String            promotion;

    /** 到期时间 */
    private LocalDateTime     expire;

    /** 锁定到期时间 */
    private LocalDateTime     lockExpire;

    /** 是否已分配库存 */
    @TableField(value = "is_allocated_stock")
    private Boolean           allocatedStock;

    /** 支付方式名称 */
    private String            paymentMethodName;

    /** 配送方式名称 */
    private String            shippingMethodName;

    /** 地区 */
    private Long              areaId;

    /** 支付方式 */
    private Long              paymentMethodId;

    /** 配送方式 */
    private Long              shippingMethodId;

    /** 操作员 */
    private Long              operatorId;

    /** 会员 */
    private Long              userId;

    /** 优惠码 */
    private Long              couponCodeId;

}
