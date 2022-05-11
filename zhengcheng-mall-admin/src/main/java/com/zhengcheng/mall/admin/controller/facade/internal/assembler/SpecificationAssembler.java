package com.zhengcheng.mall.admin.controller.facade.internal.assembler;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.zhengcheng.mall.admin.controller.command.SpecificationCommand;
import com.zhengcheng.mall.admin.controller.dto.SpecificationDTO;
import com.zhengcheng.mall.domain.entity.Specification;

/**
 * SpecificationAssembler
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 15:39
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SpecificationAssembler {

    @Mappings({ @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss"), })
    SpecificationDTO toDTO(Specification specification);

    Specification toEntity(SpecificationCommand specificationCommand);
}
