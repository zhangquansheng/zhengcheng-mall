package com.zhengcheng.mall.api.command;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会员充值
 *
 * @author quansheng1.zhang
 * @since 2022/5/15 12:31
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRechargeCommand implements Serializable {
    private static final long serialVersionUID = 557963452849765907L;

    @ApiModelProperty("充值总金额")
    private String            totalAmount;
}
