package com.zhengcheng.mall.admin.controller.command;

import javax.validation.constraints.NotNull;

import com.zhengcheng.common.web.UserCommand;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * SpecificationValueRemoveCommand
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 15:13
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecificationValueRemoveCommand extends UserCommand {

    private static final long serialVersionUID = -3550226997061584020L;

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID不能为空")
    private Long              id;
}
