package com.zhengcheng.mall.api.controller.facade.internal.assembler;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.zhengcheng.mall.api.controller.command.UserRoleCommand;
import com.zhengcheng.mall.api.controller.facade.internal.dto.UserRoleDTO;
import com.zhengcheng.mall.domain.entity.UserRole;

/**
 * 用户角色表(UserRole)封装类
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:26:58
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserRoleAssembler {

    UserRoleDTO toDTO(UserRole userRole);

    UserRole toEntity(UserRoleCommand userRoleCommand);

    List<UserRoleDTO> toDTOs(List<UserRole> userRoles);
}
