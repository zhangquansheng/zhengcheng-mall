package com.zhengcheng.mall.admin.controller.facade;

import com.zhengcheng.mall.admin.controller.command.SpecificationValueCommand;
import com.zhengcheng.mall.admin.controller.dto.SpecificationValueDTO;

/**
 * SpecificationValueFacade
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 14:37
 */
public interface SpecificationValueFacade {

    /**
     * 通过ID删除单条数据
     * @param id
     *          主键
     */
    boolean removeById(Long id);

    /**
     * 添加单条数据
     *
     * @param specificationValueCommand
     *            数据查询对象
     */
    SpecificationValueDTO add(SpecificationValueCommand specificationValueCommand);
}
