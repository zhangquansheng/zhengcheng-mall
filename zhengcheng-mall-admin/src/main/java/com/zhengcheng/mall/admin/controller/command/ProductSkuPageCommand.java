package com.zhengcheng.mall.admin.controller.command;

import com.zhengcheng.common.web.PageCommand;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * ProductSkuPageCommand
 *
 * @author quansheng1.zhang
 * @since 2022/5/17 11:28
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSkuPageCommand extends PageCommand {
    private static final long serialVersionUID = -6807331036057140408L;

    @ApiModelProperty("SPU ID")
    private Long              spuId;
}
