package com.zhengcheng.mall.admin.controller.facade.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.ProductSkuCommand;
import com.zhengcheng.mall.admin.controller.dto.ProductSpuDTO;
import com.zhengcheng.mall.admin.controller.dto.SkuDataDTO;
import com.zhengcheng.mall.admin.controller.facade.ProductSpuFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.ProductAssembler;
import com.zhengcheng.mall.domain.entity.ProductSku;
import com.zhengcheng.mall.domain.entity.ProductSpecificationValue;
import com.zhengcheng.mall.domain.entity.ProductSpu;
import com.zhengcheng.mall.domain.mapper.ProductSpecificationValueMapper;
import com.zhengcheng.mall.service.ProductSkuService;
import com.zhengcheng.mall.service.ProductSpecificationValueService;
import com.zhengcheng.mall.service.ProductSpuService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
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
                jsonObject.put(sb2.toString(), BeanUtil.getFieldValue(productSku, propDesc.getField().getName()) + "");
            }
        });
        return jsonObject;
    }

    @Override
    public void saveSkuData(JSONObject sku) {
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
        BeanDesc desc = BeanUtil.getBeanDesc(ProductSku.class);
        //        List<ProductSku> productSkus = new ArrayList<>();
        //        List<ProductSpecification> productSpecifications = new ArrayList<>();
        //        List<ProductSpecificationValue> productSpecificationValues = new ArrayList<>();

        List<SkuDataDTO> skuDataDTOList = new ArrayList<>();
        for (Map.Entry entry : sku.entrySet()) {
            String key = (String) entry.getKey();
            if (key.startsWith("skus")) {
                //                System.out.println(String.valueOf(entry.getKey()));
                // 截取规格
                //                System.out.println();
                //                System.out.println();

                SkuDataDTO skuDataDTO = new SkuDataDTO();
                skuDataDTO.setKey(StrUtil.subBefore(key, "]", false).replace("skus[", ""));
                skuDataDTO.setPropName(StrUtil.subAfter(key, "[", true).replace("]", ""));
                skuDataDTO.setValue((String) entry.getValue());
                skuDataDTOList.add(skuDataDTO);
                /**
                 * SELECT
                 * 	product_sku_id 
                 * FROM
                 * 	product_specification_value 
                 * WHERE
                 * 	specification_value_id IN ( 6, 9 ) 
                 * GROUP BY
                 * 	product_sku_id 
                 * HAVING
                 * 	COUNT( specification_value_id ) = 2
                 */
            }
        }

        Map<String, List<SkuDataDTO>> groupBy = skuDataDTOList.stream()
                .collect(Collectors.groupingBy(SkuDataDTO::getKey));

        List<ProductSkuCommand> productSkuCommands = new ArrayList<>();
        for (Map.Entry entry : groupBy.entrySet()) {
            String key = (String) entry.getKey();
            System.out.println(key);

            ProductSkuCommand productSkuCommand = new ProductSkuCommand();
            productSkuCommand.setSpecificationValueIds(getSpecificationValueIds(key));

            List<SkuDataDTO> skuDatas = (List<SkuDataDTO>) entry.getValue();
            skuDatas.forEach(skuDataDTO -> {
                try {
                    BeanUtil.setFieldValue(productSkuCommand, skuDataDTO.getPropName(), skuDataDTO.getValue());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });

            productSkuCommands.add(productSkuCommand);
        }

        productSkuCommands.forEach(productSkuCommand -> {
            Long skuId = productSpecificationValueMapper.findProductSkuId(productSkuCommand.getSpecificationValueIds(),
                    productSkuCommand.getSpecificationValueIds().size());
            productSkuCommand.setId(skuId);
        });

        System.out.println(JSONUtil.toJsonStr(productSkuCommands));
    }

    @Autowired
    private ProductSpecificationValueMapper productSpecificationValueMapper;

    private List<Long> getSpecificationValueIds(String specificationValueIds) {
        String[] idStrs = specificationValueIds.split("-");
        List<Long> ids = new ArrayList<>();
        for (String idStr : idStrs) {
            ids.add(Long.valueOf(idStr));
        }
        return ids;
    }
}
