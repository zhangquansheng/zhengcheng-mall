package com.zhengcheng.mall.admin.controller.facade.internal.assembler;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.zhengcheng.mall.admin.controller.command.ProductCategoryCommand;
import com.zhengcheng.mall.admin.controller.dto.ProductCategoryDTO;
import com.zhengcheng.mall.domain.entity.ProductCategory;

/**
 * ProductCategoryAssembler
 *
 * @author quansheng1.zhang
 * @since 2022/5/15 14:55
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductCategoryAssembler {

    @Mappings({ @Mapping(target = "title", source = "name"), })
    ProductCategoryDTO toDTO(ProductCategory productCategory);

    ProductCategory toEntity(ProductCategoryCommand productCategoryCommand);

    List<ProductCategoryDTO> toDTOs(List<ProductCategory> productCategories);
}
