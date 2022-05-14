package com.zhengcheng.mall.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhengcheng.mall.domain.entity.ProductSpecificationValue;

/**
 * ProductSpecificationValueMapper
 *
 * @author quansheng1.zhang
 * @since 2022/5/12 10:41
 */
public interface ProductSpecificationValueMapper extends BaseMapper<ProductSpecificationValue> {

    /**
     * 根据规格值查询对应的商品skuId
     * @param spuId SPU ID
     * @param specificationValueIds 规格值ID
     * @param specificationValueCount 规格值数量                              
     * @return skuId
     */
    Long findProductSkuId(@Param("spuId") Long spuId, @Param("specificationValueIds") List<Long> specificationValueIds,
                          @Param("specificationValueCount") Integer specificationValueCount);
}
