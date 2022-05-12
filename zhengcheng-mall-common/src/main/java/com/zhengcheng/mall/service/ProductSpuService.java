package com.zhengcheng.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhengcheng.mall.common.command.ProductSpuCommand;
import com.zhengcheng.mall.domain.entity.ProductSpu;

/**
 * ProductSpuService
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 22:18
 */
public interface ProductSpuService extends IService<ProductSpu> {

    /**
     * 添加SKU
     * @param productSpuCommand ProductSpuDTO
     */
    void addSku(ProductSpuCommand productSpuCommand);
}
