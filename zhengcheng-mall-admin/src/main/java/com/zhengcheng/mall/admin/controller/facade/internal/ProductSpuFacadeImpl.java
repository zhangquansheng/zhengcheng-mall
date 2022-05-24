package com.zhengcheng.mall.admin.controller.facade.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.exception.BizException;
import com.zhengcheng.common.holder.ZcUserInfoHolder;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.dto.ProductSpuDTO;
import com.zhengcheng.mall.admin.controller.dto.SkuTableDataDTO;
import com.zhengcheng.mall.admin.controller.facade.ProductSpuFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.ProductAssembler;
import com.zhengcheng.mall.common.command.ProductSkuCommand;
import com.zhengcheng.mall.common.command.ProductSpuCommand;
import com.zhengcheng.mall.domain.entity.ProductSku;
import com.zhengcheng.mall.domain.entity.ProductSpecificationValue;
import com.zhengcheng.mall.domain.entity.ProductSpu;
import com.zhengcheng.mall.service.ProductSkuService;
import com.zhengcheng.mall.service.ProductSpecificationValueService;
import com.zhengcheng.mall.service.ProductSpuService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductSpuFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 22:25
 */
@Slf4j
@Service
public class ProductSpuFacadeImpl implements ProductSpuFacade {

    @Autowired
    private ProductSpuService                productSpuService;
    @Autowired
    private ProductSkuService                productSkuService;
    @Autowired
    private ProductSpecificationValueService productSpecificationValueService;
    @Autowired
    private ProductAssembler                 productAssembler;
    @Autowired
    private RedissonClient                   redissonClient;

    @Override
    public ProductSpuDTO findById(Long spuId) {
        ProductSpu productSpu = productSpuService.getById(spuId);
        return productAssembler.toDTO(productSpu);
    }

    @Override
    public PageInfo<ProductSpuDTO> page(PageCommand pageCommand) {
        IPage<ProductSpu> page = productSpuService.page(PageUtil.getPage(pageCommand),
                new LambdaQueryWrapper<ProductSpu>().orderByDesc(ProductSpu::getCreateTime));

        PageInfo<ProductSpuDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(productAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }

    /**
     * 获取统一属性的SKU
     * @return SKU
     */
    private JSONObject getUnifiedAttributeProductSku(Long spuId) {
        JSONObject jsonObject = new JSONObject();
        ProductSku productSku = productSkuService
                .getOne(new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getSpuId, spuId).last("limit 1"));
        jsonObject.put("marketPrice", productSku.getMarketPrice());
        jsonObject.put("cost", productSku.getCost());
        jsonObject.put("stock", productSku.getStock());
        jsonObject.put("price", productSku.getPrice());
        return jsonObject;
    }

    /**
     * 获取多属性的SKU列表
     * @return SKU列表
     */
    private JSONObject getMultiAttributeProductSku(Long spuId) {
        BeanDesc desc = BeanUtil.getBeanDesc(ProductSku.class);
        JSONObject jsonObject = new JSONObject();
        // 多规格
        List<ProductSku> productSkus = productSkuService
                .list(new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getSpuId, spuId));
        List<ProductSpecificationValue> allProductSpecificationValues = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(productSkus)) {
            allProductSpecificationValues = productSpecificationValueService.list(
                    new LambdaQueryWrapper<ProductSpecificationValue>().in(ProductSpecificationValue::getProductSkuId,
                            productSkus.stream().map(ProductSku::getId).collect(Collectors.toList())));
        }

        List<ProductSpecificationValue> finalAllProductSpecificationValues = allProductSpecificationValues;
        productSkus.forEach(productSku -> {
            StrBuilder sb = new StrBuilder();
            sb.append("skus[");
            List<ProductSpecificationValue> productSpecificationValues = finalAllProductSpecificationValues.stream()
                    .filter(s -> productSku.getId().equals(s.getProductSkuId())).collect(Collectors.toList());
            sb.append(productSpecificationValues.stream().map(psv -> psv.getSpecificationValueId() + "")
                    .collect(Collectors.joining("-")));
            sb.append("]");
            for (PropDesc propDesc : desc.getProps()) {
                StrBuilder sb2 = new StrBuilder();
                sb2.append(sb.toString());
                sb2.append("[");
                sb2.append(propDesc.getField().getName());
                sb2.append("]");
                jsonObject.put(sb2.toString(), getProductSkuFieldValue(productSku, propDesc));
            }
        });
        log.info("spuId: {} , skuData: {}", spuId, JSONUtil.toJsonStr(jsonObject));
        return jsonObject;
    }

    @Override
    public JSONObject skuData(Long spuId) {
        ProductSpu productSpu = productSpuService.getById(spuId);
        // 统一规格
        if (productSpu.getSpecificationMode().equals(0)) {
            return getUnifiedAttributeProductSku(spuId);
        }
        // 多规格
        return getMultiAttributeProductSku(spuId);
    }

    private String getProductSkuFieldValue(ProductSku productSku, PropDesc propDesc) {
        Object value = BeanUtil.getFieldValue(productSku, propDesc.getField().getName());
        if (value instanceof Boolean) {
            return Boolean.TRUE.equals(value) ? "1" : "0";
        }
        return String.valueOf(value);
    }

    @Override
    public void idempotentSaveSkuData(Long spuId, JSONObject sku) {
        String lockName = StrUtil.format("zc:mall:save:sku:spu:id:{}", spuId);
        // 可重入锁（Reentrant Lock）
        RLock lock = redissonClient.getLock(lockName);
        try {
            // 获取锁
            if (lock.tryLock(1, 5, TimeUnit.SECONDS)) {
                this.saveSkuData(spuId, sku);
                return;
            }
            throw new BizException("正在更新SKU中...");
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            throw new BizException("正在更新SKU中...");
        } catch (BizException e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            // 释放锁
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    /**
     * 获取统一规格SKU入参
     * @param spuId SPU ID
     * @param sku SKU
     * @return 入参
     */
    private List<ProductSkuCommand> getUnifiedAttributeProductSkus(Long spuId, JSONObject sku) {
        //{"is_attribute":"0","price":"1010","marketPrice":"1000","cost":"1000","stock":"1000","enable":"1"}
        List<ProductSkuCommand> productSkus = new ArrayList<>();
        ProductSkuCommand productSkuCommand = new ProductSkuCommand();
        productSkuCommand.setPrice(sku.getLong("price"));
        productSkuCommand.setCost(sku.getLong("cost"));
        productSkuCommand.setMarketPrice(sku.getLong("marketPrice"));
        productSkuCommand.setStock(sku.getInteger("stock"));
        productSkuCommand.setPicture("");
        productSkuCommand.setEnable(sku.getString("enable").equals("1") ? Boolean.TRUE : Boolean.FALSE);
        productSkuCommand.setSpuId(spuId);
        productSkus.add(productSkuCommand);
        return productSkus;
    }

    @Override
    public void saveSkuData(Long spuId, JSONObject sku) {
        List<ProductSkuCommand> productSkus = new ArrayList<>();
        List<Long> specificationValueIds = new ArrayList<>();
        Integer specificationMode = sku.getInteger("is_attribute");
        if (specificationMode == 0) {
            productSkus = getUnifiedAttributeProductSkus(spuId, sku);
        } else {
            /**
             * {
             *     "is_attribute":"1",
             *     "product_type":"1",
             *     "attribute_value[1]":"N20R093001",
             *     "skus[5-9][picture]":"",
             *     "file":"",
             *     "skus[5-9][price]":"1000",
             *     "skus[5-9][marketPrice]":"1000",
             *     "skus[5-9][cost]":"1000",
             *     "skus[5-9][stock]":"1",
             *     "skus[5-9][enable]":"1",
             *     "skus[6-9][picture]":"",
             *     "skus[6-9][price]":"1000",
             *     "skus[6-9][marketPrice]":"1000",
             *     "skus[6-9][cost]":"1000",
             *     "skus[6-9][stock]":"10",
             *     "skus[6-9][enable]":"1"
             * }
             */
            List<SkuTableDataDTO> skuTableDataDTOList = new ArrayList<>();
            for (Map.Entry entry : sku.entrySet()) {
                String key = (String) entry.getKey();
                if (key.startsWith("skus")) {
                    // 截取规格
                    SkuTableDataDTO skuTableDataDTO = new SkuTableDataDTO();
                    skuTableDataDTO.setKey(StrUtil.subBefore(key, "]", false).replace("skus[", ""));
                    skuTableDataDTO.setPropName(StrUtil.subAfter(key, "[", true).replace("]", ""));
                    skuTableDataDTO.setValue((String) entry.getValue());
                    skuTableDataDTOList.add(skuTableDataDTO);
                }
            }
            log.info("skuTableDataDTOList：{}", JSONUtil.toJsonStr(skuTableDataDTOList));

            Map<String, List<SkuTableDataDTO>> groupBy = skuTableDataDTOList.stream()
                    .collect(Collectors.groupingBy(SkuTableDataDTO::getKey));
            for (Map.Entry entry : groupBy.entrySet()) {
                String key = (String) entry.getKey();
                ProductSkuCommand productSku = new ProductSkuCommand();
                productSku.setSpecificationValueIds(getSpecificationValueIds(key));
                specificationValueIds.addAll(productSku.getSpecificationValueIds());

                List<SkuTableDataDTO> skuDatas = (List<SkuTableDataDTO>) entry.getValue();
                skuDatas.forEach(skuTableDataDTO -> {
                    try {
                        BeanUtil.setFieldValue(productSku, skuTableDataDTO.getPropName(), skuTableDataDTO.getValue());
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                });
                productSkus.add(productSku);
            }
        }

        ProductSpuCommand productSpuCommand = new ProductSpuCommand();
        productSpuCommand.setSkus(productSkus);
        productSpuCommand
                .setSpecificationValueIds(specificationValueIds.stream().distinct().collect(Collectors.toList()));
        productSpuCommand.setId(spuId);
        productSpuCommand.setProductCategoryId(sku.getLong("product_type"));
        productSpuCommand.setSpecificationMode(specificationMode);
        productSpuCommand.setUpdateUserId(ZcUserInfoHolder.getUserId());
        productSpuCommand.setUpdateUserName(ZcUserInfoHolder.getUsername());
        productSpuService.addSku(productSpuCommand);
    }

    private List<Long> getSpecificationValueIds(String specificationValueIds) {
        String[] idStrs = specificationValueIds.split("-");
        List<Long> ids = new ArrayList<>();
        for (String idStr : idStrs) {
            ids.add(Long.valueOf(idStr));
        }
        return ids;
    }
}
