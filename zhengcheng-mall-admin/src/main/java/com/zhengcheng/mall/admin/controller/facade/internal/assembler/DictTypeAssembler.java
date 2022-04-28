package com.zhengcheng.mall.admin.controller.facade.internal.assembler;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.zhengcheng.mall.admin.controller.dto.DictTypeDTO;
import com.zhengcheng.mall.domain.entity.DictType;

/**
 * DictTypeAssembler
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 20:36
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DictTypeAssembler {

    @Mappings({ @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss"), })
    DictTypeDTO toDTO(DictType dictType);

    List<DictTypeDTO> toDTOs(List<DictType> dictTypes);
}
