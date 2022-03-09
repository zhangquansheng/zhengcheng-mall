package com.zhengcheng.mall.api.controller.facade.internal.assembler;

import java.util.List;

import com.zhengcheng.mall.domain.entity.Authority;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.zhengcheng.mall.api.controller.command.AuthorityCommand;
import com.zhengcheng.mall.api.controller.facade.internal.dto.AuthorityDTO;
import com.zhengcheng.mall.domain.entity.Authority;

/**
 * 权限表(Authority)封装类
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:46:58
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AuthorityAssembler {

    @Mappings({
            @Mapping(target = "type",source = "type.value"),
    })
    AuthorityDTO toDTO(Authority authority);

    @Mappings({
            @Mapping(target = "type",expression = "java(com.zhengcheng.mall.domain.enums.AuthorityTypeEnum.getByValue(authorityCommand.getType()))"),
    })
    Authority toEntity(AuthorityCommand authorityCommand);

    List<AuthorityDTO> toDTOs(List<Authority> authoritys);
}
