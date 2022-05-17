package com.zhengcheng.mall.admin.controller.facade;

import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.ProductSkuPageCommand;
import com.zhengcheng.mall.admin.controller.dto.ProductSkuDTO;

/**
 * ProductSkuFacade
 *
 * @author quansheng1.zhang
 * @since 2022/5/17 11:26
 */
public interface ProductSkuFacade {

    /**
     * 分页查询
     *
     * @param productSkuPageCommand
     *            分页参数
     * @return 数据查询对象
     */
    PageInfo<ProductSkuDTO> page(ProductSkuPageCommand productSkuPageCommand);
}
