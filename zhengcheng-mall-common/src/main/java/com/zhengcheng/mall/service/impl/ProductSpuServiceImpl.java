package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.ProductSpu;
import com.zhengcheng.mall.domain.mapper.ProductSpuMapper;
import com.zhengcheng.mall.service.ProductSpuService;

/**
 * ProductSpuServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 22:19
 */
@Service
public class ProductSpuServiceImpl extends ServiceImpl<ProductSpuMapper, ProductSpu> implements ProductSpuService {
}
