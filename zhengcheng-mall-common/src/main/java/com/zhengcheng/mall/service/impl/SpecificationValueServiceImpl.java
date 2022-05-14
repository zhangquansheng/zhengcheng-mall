package com.zhengcheng.mall.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.ProductSpecificationValue;
import com.zhengcheng.mall.domain.entity.SpecificationValue;
import com.zhengcheng.mall.domain.mapper.ProductSpecificationValueMapper;
import com.zhengcheng.mall.domain.mapper.SpecificationValueMapper;
import com.zhengcheng.mall.service.SpecificationValueService;

/**
 * SpecificationValueServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 13:38
 */
@Service
public class SpecificationValueServiceImpl extends ServiceImpl<SpecificationValueMapper, SpecificationValue>
        implements SpecificationValueService {

    @Autowired
    private SpecificationValueMapper        specificationValueMapper;
    @Autowired
    private ProductSpecificationValueMapper productSpecificationValueMapper;

    @Override
    public List<Long> findSpecificationValueBySpuId(Long spuId) {
        return specificationValueMapper.findSpecificationValueBySpuId(spuId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        // 删除规格对应的sku记录
        productSpecificationValueMapper.delete(new LambdaUpdateWrapper<ProductSpecificationValue>()
                .eq(ProductSpecificationValue::getSpecificationValueId, id));

        return super.removeById(id);
    }
}
