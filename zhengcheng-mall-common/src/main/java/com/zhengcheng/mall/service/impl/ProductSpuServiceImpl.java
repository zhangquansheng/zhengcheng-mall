package com.zhengcheng.mall.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.common.exception.BizException;
import com.zhengcheng.mall.common.command.ProductSpuCommand;
import com.zhengcheng.mall.domain.entity.ProductSku;
import com.zhengcheng.mall.domain.entity.ProductSpecificationValue;
import com.zhengcheng.mall.domain.entity.ProductSpu;
import com.zhengcheng.mall.domain.entity.Specification;
import com.zhengcheng.mall.domain.mapper.*;
import com.zhengcheng.mall.service.ProductSpuService;

import cn.hutool.core.bean.BeanUtil;

/**
 * ProductSpuServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 22:19
 */
@Service
public class ProductSpuServiceImpl extends ServiceImpl<ProductSpuMapper, ProductSpu> implements ProductSpuService {

    @Autowired
    private ProductSpecificationValueMapper productSpecificationValueMapper;
    @Autowired
    private ProductSpuMapper                productSpuMapper;
    @Autowired
    private ProductSkuMapper                productSkuMapper;
    @Autowired
    private SpecificationValueMapper        specificationValueMapper;
    @Autowired
    private SpecificationMapper             specificationMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addSku(ProductSpuCommand productSpuCommand) {
        // 设置skuId
        productSpuCommand.getSkus().forEach(sku -> {
            Long skuId = productSpecificationValueMapper.findProductSkuId(sku.getSpecificationValueIds(),
                    sku.getSpecificationValueIds().size());
            sku.setId(skuId);
        });

        // 更新spu
        ProductSpu spu = productSpuMapper.selectById(productSpuCommand.getId());
        if (Objects.isNull(spu)) {
            throw new BizException("spuId有误，未查询到对应的记录！");
        }

        BeanUtils.copyProperties(productSpuCommand, spu);
        productSpuMapper.updateById(spu);

        // 添加sku
        List<Long> existsSkuIds = new ArrayList<>();
        productSpuCommand.getSkus().forEach(skuDTO -> {
            if (Objects.isNull(skuDTO.getId())) {
                ProductSku sku = new ProductSku();
                BeanUtil.copyProperties(skuDTO, sku);
                // 暂时去当前时间戳为编号
                sku.setSkuNo(String.valueOf(System.currentTimeMillis()));
                sku.setSpuId(spu.getId());
                sku.setSpuNo(spu.getSpuNo());
                productSkuMapper.insert(sku);

                existsSkuIds.add(sku.getId());
                skuDTO.setId(sku.getId());
            } else {
                ProductSku sku = productSkuMapper.selectById(skuDTO.getId());
                BeanUtil.copyProperties(skuDTO, sku);
                productSkuMapper.updateById(sku);

                existsSkuIds.add(sku.getId());
            }
        });
        // 删除其他的SKU
        productSkuMapper.delete(new LambdaUpdateWrapper<ProductSku>().notIn(ProductSku::getId, existsSkuIds));

        List<Long> specificationIds = specificationValueMapper
                .findSpecificationIdsByIds(productSpuCommand.getSpecificationValueIds()).stream().distinct()
                .collect(Collectors.toList());
        // 修改 specification 规格表的 sort
        for (int sort = 0; sort < specificationIds.size(); sort++) {
            specificationMapper.update(null,
                    new LambdaUpdateWrapper<Specification>().set(Specification::getSort, sort)
                            .set(Specification::getUpdateUserId, productSpuCommand.getUpdateUserId())
                            .eq(Specification::getId, specificationIds.get(sort)));
        }

        // 删除所有SKU的规格值
        productSpecificationValueMapper.delete(new LambdaUpdateWrapper<ProductSpecificationValue>()
                .in(ProductSpecificationValue::getProductSkuId, existsSkuIds));
        // 添加sku对应的规格值
        productSpuCommand.getSkus().forEach(skuDTO -> skuDTO.getSpecificationValueIds().forEach(svId -> {
            ProductSpecificationValue productSpecificationValue = new ProductSpecificationValue();
            productSpecificationValue.setProductSkuId(skuDTO.getId());
            productSpecificationValue.setSpecificationValueId(svId);
            productSpecificationValue.setCreateUserId(skuDTO.getUpdateUserId());
            productSpecificationValue.setUpdateUserId(skuDTO.getUpdateUserId());
            productSpecificationValueMapper.insert(productSpecificationValue);
        }));
    }
}
