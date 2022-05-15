package com.zhengcheng.mall.admin.controller.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品分类
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 12:09
 */
@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryDTO implements Serializable {

    private static final long serialVersionUID = 2670960277457745620L;

    @ApiModelProperty("ID")
    private Long              id;
    @ApiModelProperty("名称")
    private String            title;
    @ApiModelProperty("父ID")
    private Long              pid;
    @ApiModelProperty("层级(最多三级1,2,3)")
    private Integer           level;
    @ApiModelProperty("排序")
    private Integer           sort;
    @ApiModelProperty("是否启用")
    private boolean           enable;
}
