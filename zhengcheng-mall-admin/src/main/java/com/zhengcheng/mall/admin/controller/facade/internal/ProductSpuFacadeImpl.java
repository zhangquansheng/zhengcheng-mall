package com.zhengcheng.mall.admin.controller.facade.internal;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.dto.ProductSpuDTO;
import com.zhengcheng.mall.admin.controller.facade.ProductSpuFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.ProductAssembler;
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
import cn.hutool.core.text.StrBuilder;
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

    @Override
    public JSONObject skuData(Long spuId) {
        JSONObject jsonObject = new JSONObject();

        BeanDesc desc = BeanUtil.getBeanDesc(ProductSku.class);

        List<ProductSku> productSkus = productSkuService
                .list(new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getSpuId, spuId));
        List<ProductSpecificationValue> allProductSpecificationValues = productSpecificationValueService
                .list(new LambdaQueryWrapper<ProductSpecificationValue>().in(ProductSpecificationValue::getProductSkuId,
                        productSkus.stream().map(ProductSku::getId).collect(Collectors.toList())));

        productSkus.forEach(productSku -> {
            StrBuilder sb = new StrBuilder();
            sb.append("skus[");
            List<ProductSpecificationValue> productSpecificationValues = allProductSpecificationValues.stream()
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
                jsonObject.put(sb2.toString(), BeanUtil.getFieldValue(productSku, propDesc.getField().getName()));
            }
        });
        return jsonObject;
    }
}
