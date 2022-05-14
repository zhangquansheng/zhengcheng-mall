package com.zhengcheng.mall.admin.controller.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zhengcheng.common.validation.annotation.Insert;
import com.zhengcheng.common.web.UserCommand;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * SpecificationCommand
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 15:36
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecificationCommand extends UserCommand {
    private static final long serialVersionUID = 330238234748769927L;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String            name;

    @ApiModelProperty(value = "商品分类ID")
    @NotNull(message = "商品分类ID不能为空", groups = { Insert.class })
    private Long              productCategoryId;
}
