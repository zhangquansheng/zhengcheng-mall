package com.zhengcheng.mall.admin.controller.command;

import com.zhengcheng.common.web.PageCommand;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * PaymentPageCommand
 *
 * @author quansheng1.zhang
 * @since 2022/5/20 11:15
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentPageCommand extends PageCommand {
    private static final long serialVersionUID = 8661101171888235925L;

    @ApiModelProperty("交易编号")
    private String            tradeNo;
}
