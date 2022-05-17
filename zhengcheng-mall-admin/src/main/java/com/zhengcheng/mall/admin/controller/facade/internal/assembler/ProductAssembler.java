package com.zhengcheng.mall.admin.controller.facade.internal.assembler;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.zhengcheng.mall.admin.controller.dto.ProductSkuDTO;
import com.zhengcheng.mall.admin.controller.dto.ProductSpuDTO;
import com.zhengcheng.mall.domain.entity.ProductSku;
import com.zhengcheng.mall.domain.entity.ProductSpu;

/**
 * ProductAssembler
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 22:27
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductAssembler {

    @Mappings({ @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss"), })
    ProductSpuDTO toDTO(ProductSpu productSpu);

    List<ProductSpuDTO> toDTOs(List<ProductSpu> productSpus);

    @Mappings({ @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss"), })
    ProductSkuDTO toSkuDTO(ProductSku productSku);

    List<ProductSkuDTO> toSkuDTOs(List<ProductSku> productSku);
}
