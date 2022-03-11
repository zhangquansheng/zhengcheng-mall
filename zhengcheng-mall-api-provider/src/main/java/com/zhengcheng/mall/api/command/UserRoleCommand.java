package com.zhengcheng.mall.api.command;

import java.util.List;

import com.zhengcheng.common.web.UserCommand;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 用户角色表(UserRole)数据查询对象
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:26:59
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRoleCommand extends UserCommand {
    private static final long serialVersionUID = 414393407160989807L;

    @ApiModelProperty("用户ID(user表ID)")
    private Long userId;

    @ApiModelProperty("角色ID(role表ID)列表")
    private List<Long> roleIds;

}
