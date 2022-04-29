package com.zhengcheng.mall.admin.controller.facade.internal.assembler;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.zhengcheng.mall.admin.controller.dto.DictDataDTO;
import com.zhengcheng.mall.domain.entity.DictData;

/**
 * DictDataAssembler
 *
 * @author quansheng1.zhang
 * @since 2022/4/29 15:08
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface DictDataAssembler {

    @Mappings({ @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss"), })
    DictDataDTO toDTO(DictData dictData);

    List<DictDataDTO> toDTOs(List<DictData> dictDatas);

}
