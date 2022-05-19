/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 *  预存款
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 20:22
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("deposit")
public class Deposit extends BaseEntity {

    private static final long serialVersionUID = -8323452873046981882L;

    /**
     * 类型
     */
    public enum Type {

        /** 会员充值 */
        memberRecharge,

        /** 会员支付 */
        memberPayment,

        /** 后台充值 */
        adminRecharge,

        /** 后台扣费 */
        adminChargeback,

        /** 后台支付 */
        adminPayment,

        /** 后台退款 */
        adminRefunds
    }

    /** 类型 */
    private Type    type;

    /** 收入金额，单位分 */
    private Integer credit;

    /** 支出金额，单位分 */
    private Integer debit;

    /** 当前余额，单位分 */
    private Integer balance;

    /** 操作员 */
    private String  operator;

    /** 备注 */
    private String  memo;

    /** 会员ID */
    private Long    userId;

    /** 收款单 */
    private Long    paymentId;

}
