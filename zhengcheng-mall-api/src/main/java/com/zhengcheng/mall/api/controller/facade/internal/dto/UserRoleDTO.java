package com.zhengcheng.mall.api.controller.facade.internal.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色表(UserRole)数据传输对象
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:26:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleDTO implements Serializable {
    private static final long serialVersionUID = -95854532738238016L;
    @ApiModelProperty("用户ID(user表ID)")
    private Long userId;
    @ApiModelProperty("角色ID(role表ID)")
    private Long roleId;

}
