package com.zhengcheng.mall.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhengcheng.mall.domain.entity.SpecificationValue;

/**
 * SpecificationValueService
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 13:36
 */
public interface SpecificationValueService extends IService<SpecificationValue> {

    /**
     * 查询spu的规格值
     * @param spuId spuId
     * @return 规格值列表
     */
    List<Long> findSpecificationValueBySpuId(Long spuId);
}
