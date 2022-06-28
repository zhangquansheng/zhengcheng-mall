package com.zhengcheng.mall.admin.controller.command;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SpecificationValueRemoveCommand
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 15:13
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecificationValueRemoveCommand implements Serializable {

    private static final long serialVersionUID = -3550226997061584020L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID不能为空")
    private Long              id;
}
