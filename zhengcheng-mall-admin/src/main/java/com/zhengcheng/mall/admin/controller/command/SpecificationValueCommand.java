package com.zhengcheng.mall.admin.controller.command;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zhengcheng.common.validation.annotation.Insert;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SpecificationValueCommand
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 14:34
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecificationValueCommand implements Serializable {
    private static final long serialVersionUID = 8188279965811937811L;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String            name;

    @ApiModelProperty(value = "规格ID")
    @NotNull(message = "规格ID不能为空", groups = { Insert.class })
    private Long              specificationId;
}
