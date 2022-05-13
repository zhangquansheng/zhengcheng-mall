package com.zhengcheng.mall.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.ProductSpecificationValue;
import com.zhengcheng.mall.domain.entity.Specification;
import com.zhengcheng.mall.domain.entity.SpecificationValue;
import com.zhengcheng.mall.domain.mapper.ProductSpecificationValueMapper;
import com.zhengcheng.mall.domain.mapper.SpecificationMapper;
import com.zhengcheng.mall.domain.mapper.SpecificationValueMapper;
import com.zhengcheng.mall.service.SpecificationService;

/**
 * SpecificationServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 13:37
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification>
        implements SpecificationService {

    @Autowired
    private SpecificationValueMapper        specificationValueMapper;
    @Autowired
    private ProductSpecificationValueMapper productSpecificationValueMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        // 删除规格对应的sku记录
        List<SpecificationValue> specificationValues = specificationValueMapper.selectList(
                new LambdaQueryWrapper<SpecificationValue>().eq(SpecificationValue::getSpecificationId, id));
        specificationValues.forEach(specificationValue -> productSpecificationValueMapper
                .delete(new LambdaUpdateWrapper<ProductSpecificationValue>()
                        .eq(ProductSpecificationValue::getSpecificationValueId, specificationValue.getId())));
        return super.removeById(id);
    }
}
