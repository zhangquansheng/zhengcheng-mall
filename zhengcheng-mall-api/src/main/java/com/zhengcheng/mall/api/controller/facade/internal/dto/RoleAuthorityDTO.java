package com.zhengcheng.mall.api.controller.facade.internal.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色权限表(RoleAuthority)数据传输对象
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:57:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleAuthorityDTO implements Serializable {
    private static final long serialVersionUID = 601130778864953952L;
    @ApiModelProperty("角色ID（role表ID）")
    private Long roleId;
    @ApiModelProperty("权限ID（authority表ID）")
    private Long authorityId;

}
