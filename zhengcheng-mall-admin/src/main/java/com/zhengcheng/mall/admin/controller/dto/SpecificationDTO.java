package com.zhengcheng.mall.admin.controller.dto;

import com.zhengcheng.common.dto.BaseDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * SpecificationDTO
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 15:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecificationDTO extends BaseDTO {
    private static final long serialVersionUID = -2426270570786153823L;

    @ApiModelProperty(value = "名称")
    private String            name;

    @ApiModelProperty(value = "商品分类ID")
    private Long              productCategoryId;
}
