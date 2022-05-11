package com.zhengcheng.mall.admin.controller.facade.internal.assembler;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.zhengcheng.mall.admin.controller.command.SpecificationValueCommand;
import com.zhengcheng.mall.admin.controller.dto.SpecificationValueDTO;
import com.zhengcheng.mall.domain.entity.SpecificationValue;

/**
 * SpecificationValueAssembler
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 14:40
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SpecificationValueAssembler {

    @Mappings({ @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss"), })
    SpecificationValueDTO toDTO(SpecificationValue specificationValue);

    SpecificationValue toEntity(SpecificationValueCommand specificationValueCommand);

}
