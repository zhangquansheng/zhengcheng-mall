package com.zhengcheng.mall.admin.controller.facade;

import com.alibaba.fastjson.JSONObject;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.dto.ProductSpuDTO;

/**
 * ProductSpuFacade
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 22:25
 */
public interface ProductSpuFacade {

    /**
     * 根据ID查询spu
     * @param spuId ID
     * @return spu
     */
    ProductSpuDTO findById(Long spuId);

    /**
     * 分页查询
     *
     * @param pageCommand
     *            分页参数
     * @return 数据查询对象
     */
    PageInfo<ProductSpuDTO> page(PageCommand pageCommand);

    /**
     * 根据spu查询sku数据
     * @param spuId spuId
     * @return sku数据
     */
    JSONObject skuData(Long spuId);

    /**
     * 保存 sku 数据
     * @param spuId spuId
     * @param sku sku相关参数
     */
    void saveSkuData(Long spuId, JSONObject sku);
}
