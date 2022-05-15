package com.zhengcheng.mall.admin.controller.facade.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zhengcheng.mall.admin.controller.command.ProductCategoryCommand;
import com.zhengcheng.mall.admin.controller.dto.ProductCategoryDTO;
import com.zhengcheng.mall.admin.controller.facade.ProductCategoryFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.ProductCategoryAssembler;
import com.zhengcheng.mall.domain.entity.ProductCategory;
import com.zhengcheng.mall.service.ProductCategoryService;

/**
 * ProductCategoryFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/15 14:52
 */
@Service
public class ProductCategoryFacadeImpl implements ProductCategoryFacade {

    @Autowired
    private ProductCategoryService   productCategoryService;
    @Autowired
    private ProductCategoryAssembler productCategoryAssembler;

    @Override
    public List<ProductCategoryDTO> findAll() {
        return productCategoryAssembler.toDTOs(productCategoryService.list(new LambdaQueryWrapper<ProductCategory>()
                .orderBy(Boolean.TRUE, Boolean.TRUE, ProductCategory::getSort)));
    }

    @Override
    public ProductCategoryDTO findById(Long id) {
        return productCategoryAssembler.toDTO(productCategoryService.getById(id));
    }

    @Override
    public void save(ProductCategoryCommand productCategoryCommand) {
        ProductCategory productCategory = productCategoryAssembler.toEntity(productCategoryCommand);
        productCategory.setEnable(Boolean.TRUE);
        productCategoryService.save(productCategory);
    }

    @Override
    public boolean update(ProductCategoryCommand productCategoryCommand) {
        return productCategoryService.update(new LambdaUpdateWrapper<ProductCategory>()
                .set(ProductCategory::getName, productCategoryCommand.getName())
                .set(ProductCategory::getSort, productCategoryCommand.getSort())
                .eq(ProductCategory::getId, productCategoryCommand.getId()));
    }

    @Override
    public boolean removeById(Long id) {
        return productCategoryService.removeById(id);
    }

}
