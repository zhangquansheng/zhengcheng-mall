package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.ProductCategory;
import com.zhengcheng.mall.domain.mapper.ProductCategoryMapper;
import com.zhengcheng.mall.service.ProductCategoryService;

/**
 * ProductCategoryServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/15 13:09
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory>
        implements ProductCategoryService {
}
