package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.ProductSpecificationValue;
import com.zhengcheng.mall.domain.mapper.ProductSpecificationValueMapper;
import com.zhengcheng.mall.service.ProductSpecificationValueService;

/**
 * ProductSpecificationValueServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/12 11:35
 */
@Service
public class ProductSpecificationValueServiceImpl
        extends ServiceImpl<ProductSpecificationValueMapper, ProductSpecificationValue>
        implements ProductSpecificationValueService {

}
