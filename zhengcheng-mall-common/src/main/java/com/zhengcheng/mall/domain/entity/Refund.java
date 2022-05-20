package com.zhengcheng.mall.domain.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mall.domain.enums.PaymentMethodEnum;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 退款单
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 22:08
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("refund")
public class Refund extends BaseEntity {

    private static final long serialVersionUID = 354885216604823632L;

    /** 退款流水号 */
    private String            refundNo;

    /** 方式 */
    private PaymentMethodEnum method;

    /** 支付方式 */
    private String            paymentMethod;

    /** 退款金额 */
    private BigDecimal        amount;

    /** 备注 */
    private String            memo;

    /** 订单ID */
    private Long              orderId;

}
