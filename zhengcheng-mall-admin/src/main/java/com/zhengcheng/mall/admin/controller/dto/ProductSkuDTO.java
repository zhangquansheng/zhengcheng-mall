package com.zhengcheng.mall.admin.controller.dto;

import com.zhengcheng.common.dto.BaseDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * ProductSkuDTO
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 22:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSkuDTO extends BaseDTO {

    private static final long serialVersionUID = -4529938001726985370L;

    @ApiModelProperty("SKU编号")
    private String            skuNo;
    @ApiModelProperty("全称")
    private String            fullName;
    @ApiModelProperty("销售价")
    private Long              price;
    @ApiModelProperty("成本价")
    private Long              cost;
    @ApiModelProperty("市场价")
    private Long              marketPrice;
    @ApiModelProperty("库存")
    private Integer           stock;
    @ApiModelProperty("已分配库存")
    private Integer           allocatedStock;
    @ApiModelProperty("库存备注")
    private String            stockMemo;
    @ApiModelProperty("是否启用")
    private Boolean           enable;
    @ApiModelProperty("SPU ID")
    private Long              spuId;
    @ApiModelProperty("SPU 编号")
    private String            spuNo;
}
