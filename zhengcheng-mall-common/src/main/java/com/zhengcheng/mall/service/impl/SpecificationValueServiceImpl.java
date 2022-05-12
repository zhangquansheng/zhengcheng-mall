package com.zhengcheng.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.SpecificationValue;
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
    private SpecificationValueMapper specificationValueMapper;

    @Override
    public List<Long> findSpecificationValueBySpuId(Long spuId) {
        return specificationValueMapper.findSpecificationValueBySpuId(spuId);
    }
}
