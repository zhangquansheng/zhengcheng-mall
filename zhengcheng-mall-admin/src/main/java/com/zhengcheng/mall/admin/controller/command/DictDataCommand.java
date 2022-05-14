package com.zhengcheng.mall.admin.controller.command;

import javax.validation.constraints.NotNull;

import com.zhengcheng.common.validation.annotation.Update;
import com.zhengcheng.common.web.UserCommand;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * DictDataCommand
 *
 * @author quansheng1.zhang
 * @since 2022/5/4 23:54
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictDataCommand extends UserCommand {

    private static final long serialVersionUID = 8272085074200497580L;

    @ApiModelProperty("ID，更新时候必填")
    @NotNull(message = "ID不能为空", groups = { Update.class })
    private Long              id;

    @ApiModelProperty("名称")
    private String            name;

    @ApiModelProperty("编码")
    private String            value;

    @ApiModelProperty("字典类型编码")
    private String            typeCode;
}
