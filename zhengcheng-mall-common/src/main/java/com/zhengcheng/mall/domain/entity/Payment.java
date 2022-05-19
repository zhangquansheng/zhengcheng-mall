package com.zhengcheng.mall.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Payment - 收款单 
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
@TableName("payment")
public class Payment extends BaseEntity {

    private static final long serialVersionUID = 2200130905376141270L;

    /**
     * 类型
     */
    public enum Type {

        /** 订单支付 */
        payment,

        /** 预存款充值 */
        recharge
    }

    /**
     * 方式
     */
    public enum Method {

        /** 在线支付 */
        online,

        /** 线下支付 */
        offline,

        /** 预存款支付 */
        deposit
    }

    /**
     * 状态
     */
    public enum Status {

        /** 等待支付 */
        wait,

        /** 支付成功 */
        success,

        /** 支付失败 */
        failure
    }

    /** 交易编号 */
    private String  tradeNo;

    /** 类型 */
    private Type    type;

    /** 方式 */
    private Method  method;

    /** 状态 */
    private Status  status;

    /** 支付方式 */
    private String  paymentMethod;

    /** 付款金额，单位分 */
    private Integer amount;

    /** 付款人 */
    private String  payer;

    /** 付款日期 */
    private Date    successTime;

    /** 到期时间 */
    private Date    expiredTime;

    /** 备注 */
    private String  memo;

    /** 预存款ID */
    private Long    depositId;

    /** 会员ID */
    private Long    userId;

    /** 订单ID */
    private Long    orderId;

}
