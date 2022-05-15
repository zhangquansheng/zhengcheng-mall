package com.zhengcheng.mall.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.common.exception.BizException;
import com.zhengcheng.mall.domain.entity.ProductCategory;
import com.zhengcheng.mall.domain.mapper.ProductCategoryMapper;
import com.zhengcheng.mall.service.ProductCategoryService;

import cn.hutool.core.util.StrUtil;

/**
 * ProductCategoryServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/15 13:09
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory>
        implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    /**
     * 商品分类最大的层级
     */
    public final int              PRODUCT_CATEGORY_MAX_LEVEL = 3;

    @Override
    public boolean save(ProductCategory productCategory) {
        // 设置树路径和层级
        if (Objects.isNull(productCategory.getPid()) || productCategory.getPid() <= 0) {
            productCategory.setPid(0L);
            productCategory.setLevel(1);
        } else {
            ProductCategory pProductCategory = productCategoryMapper.selectById(productCategory.getPid());
            if (Objects.isNull(pProductCategory)) {
                throw new BizException(StrUtil.format("不存在 pId:[{}] 对应的商品分类！", pProductCategory.getPid()));
            }
            if (pProductCategory.getLevel() + 1 > PRODUCT_CATEGORY_MAX_LEVEL) {
                throw new BizException(StrUtil.format("已经超过最大层级， maxLevel：[{}]", PRODUCT_CATEGORY_MAX_LEVEL));
            }

            productCategory.setLevel(pProductCategory.getLevel() + 1);
        }
        return productCategoryMapper.insert(productCategory) > 0;
    }
}
