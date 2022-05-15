package com.zhengcheng.mall.admin.controller.facade;

import java.util.List;

import com.zhengcheng.mall.admin.controller.command.ProductCategoryCommand;
import com.zhengcheng.mall.admin.controller.dto.ProductCategoryDTO;

/**
 * ProductCategoryFacade
 *
 * @author quansheng1.zhang
 * @since 2022/5/15 14:51
 */
public interface ProductCategoryFacade {
    /**
     * 查询所有商品分类
     */
    List<ProductCategoryDTO> findAll();

    /**
     * 根据ID查询
     * @param id ID
     * @return 商品分类
     */
    ProductCategoryDTO findById(Long id);

    /**
     * 保存商品分类
     * 
     * @param productCategoryCommand 商品分类
     */
    void save(ProductCategoryCommand productCategoryCommand);

    /**
     * 更新商品分类
     *
     * @param productCategoryCommand
     *            数据查询对象
     * @return 是/否           
     */
    boolean update(ProductCategoryCommand productCategoryCommand);

    /**
     * 删除 
     * @param id ID
     * @return 是/否
     */
    boolean removeById(Long id);
}
