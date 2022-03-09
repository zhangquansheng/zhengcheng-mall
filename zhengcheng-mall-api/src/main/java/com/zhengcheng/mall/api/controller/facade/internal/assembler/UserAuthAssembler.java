package com.zhengcheng.mall.api.controller.facade.internal.assembler;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.zhengcheng.mall.api.controller.command.UserAuthCommand;
import com.zhengcheng.mall.api.controller.facade.internal.dto.UserAuthDTO;
import com.zhengcheng.mall.domain.entity.UserAuth;

/**
 * 用户授权表(UserAuth)封装类
 *
 * @author quansheng1.zhang
 * @since 2021-08-14 17:28:11
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserAuthAssembler {

    UserAuthDTO toDTO(UserAuth userAuth);

    UserAuth toEntity(UserAuthCommand userAuthCommand);

    List<UserAuthDTO> toDTOs(List<UserAuth> userAuths);
}
