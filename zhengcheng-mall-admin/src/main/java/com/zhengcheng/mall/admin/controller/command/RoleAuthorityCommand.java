package com.zhengcheng.mall.admin.controller.command;

import com.zhengcheng.common.web.UserCommand;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * RoleAuthorityCommand
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:46:58
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleAuthorityCommand extends UserCommand {
    private static final long serialVersionUID = 808453051183645421L;

    @ApiModelProperty("角色ID")
    private Long              roleId;

    @ApiModelProperty("权限ID列表")
    private String            authorityIds;

}
