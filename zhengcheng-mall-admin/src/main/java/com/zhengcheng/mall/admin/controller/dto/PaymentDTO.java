package com.zhengcheng.mall.admin.controller.dto;

import com.zhengcheng.common.dto.BaseDTO;

import lombok.*;

/**
 * PaymentDTO
 *
 * @author quansheng1.zhang
 * @since 2022/5/20 11:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO extends BaseDTO {

    private static final long serialVersionUID = 4400681739346463638L;

    /** 交易编号 */
    private String            tradeNo;

    /** 类型 */
    private String            type;

    /** 类型名称 */
    private String            typeName;

    /** 方式 */
    private String            method;

    /** 方式名称 */
    private String            methodName;

    /** 状态 */
    private String            status;

    /** 状态名称 */
    private String            statusName;

    /** 支付方式 */
    private String            paymentMethod;

    /** 付款金额，单位分 */
    private Integer           amount;

    /** 付款人 */
    private String            payer;

    /** 付款日期 */
    private String            successTime;

    /** 到期时间 */
    private String            expiredTime;

    /** 备注 */
    private String            memo;

    /** 预存款ID */
    private Long              depositId;

    /** 会员ID */
    private Long              userId;

    /** 订单ID */
    private Long              orderId;
}
