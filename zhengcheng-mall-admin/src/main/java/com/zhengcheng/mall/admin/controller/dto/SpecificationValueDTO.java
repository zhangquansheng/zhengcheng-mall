package com.zhengcheng.mall.admin.controller.dto;

import com.zhengcheng.common.dto.BaseDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * SpecificationValueDTO
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 15:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecificationValueDTO extends BaseDTO {

    private static final long serialVersionUID = 8794203210247857534L;

    @ApiModelProperty("名称")
    private String            name;
}
