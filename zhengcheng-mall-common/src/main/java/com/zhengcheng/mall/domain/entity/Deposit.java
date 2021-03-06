package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mall.domain.enums.DepositTypeEnum;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 预存款
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

    /** 类型 */
    private DepositTypeEnum   type;

    /** 收入金额，单位分 */
    private Integer           credit;

    /** 支出金额，单位分 */
    private Integer           debit;

    /** 当前余额，单位分 */
    private Integer           balance;

    /** 操作员 */
    private String            operator;

    /** 备注 */
    private String            memo;

    /** 会员ID */
    private Long              userId;

    /** 收款单 */
    private Long              paymentId;

}
