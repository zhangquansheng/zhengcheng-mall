package com.zhengcheng.mall.admin.controller.dto;

import com.zhengcheng.common.dto.BaseDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * ProductSpuDTO
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 22:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSpuDTO extends BaseDTO {
    private static final long serialVersionUID = 4157349466541229785L;

    @ApiModelProperty("编号")
    private String            spuNo;

    @ApiModelProperty("名称")
    private String            name;

    @ApiModelProperty("展示图片")
    private String            image;

    @ApiModelProperty("单位")
    private String            unit;

    @ApiModelProperty("重量")
    private Integer           weight;

    @ApiModelProperty("是否上架")
    private Boolean           marketable;

    @ApiModelProperty("是否上架")
    private Boolean           top;

    @ApiModelProperty("介绍")
    private String            introduction;

    @ApiModelProperty("备注")
    private String            memo;

    @ApiModelProperty("搜索关键词")
    private String            keyword;

    @ApiModelProperty("商品分类ID")
    private Long              productCategoryId;

    @ApiModelProperty("商品分类")
    private String            productCategoryName;

    @ApiModelProperty("商品规格模式 0单规格 1多规格")
    private Integer           specificationMode;

    @ApiModelProperty("品牌ID")
    private Long              brandId;

    @ApiModelProperty("品牌")
    private String            brandName;
}
