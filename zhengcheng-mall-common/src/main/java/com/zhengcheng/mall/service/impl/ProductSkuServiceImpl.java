package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.ProductSku;
import com.zhengcheng.mall.domain.mapper.ProductSkuMapper;
import com.zhengcheng.mall.service.ProductSkuService;

/**
 * ProductSkuServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/12 11:34
 */
@Service
public class ProductSkuServiceImpl extends ServiceImpl<ProductSkuMapper, ProductSku> implements ProductSkuService {
}
