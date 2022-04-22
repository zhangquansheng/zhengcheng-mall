package com.zhengcheng.mall.admin.controller.command;

import javax.validation.constraints.NotNull;

import com.zhengcheng.common.web.UserCommand;

import lombok.*;

/**
 * EnableCommand
 *
 * @author quansheng1.zhang
 * @since 2022/4/22 11:16
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnableCommand extends UserCommand {
    private static final long serialVersionUID = 4898589400166762941L;

    /**
     * ID
     */
    @NotNull(message = "ID不能为空")
    private Long              id;
    /**
     * 是否开启
     */
    private boolean           enable;
}
