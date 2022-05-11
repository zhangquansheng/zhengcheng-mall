package com.zhengcheng.mall.admin.controller.facade;

import com.zhengcheng.mall.admin.controller.command.SpecificationCommand;
import com.zhengcheng.mall.admin.controller.dto.AttrSpecDTO;
import com.zhengcheng.mall.admin.controller.dto.SpecificationDTO;

/**
 * SpecificationFacade
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 15:34
 */
public interface SpecificationFacade {
    /**
     * 通过ID删除单条数据
     * @param id
     *          主键
     */
    boolean removeById(Long id);

    /**
     * 添加单条数据
     *
     * @param specificationCommand
     *            数据查询对象
     */
    SpecificationDTO add(SpecificationCommand specificationCommand);

    /**
     * 属性规格
     * @param productCategoryId 商品分类ID
     */
    AttrSpecDTO findAttrSpec(Long productCategoryId);
}
