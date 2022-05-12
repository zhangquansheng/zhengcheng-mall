package com.zhengcheng.mall.screw;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhengcheng.mall.BaseTest;
import com.zhengcheng.mall.domain.entity.ProductSku;
import com.zhengcheng.mall.domain.entity.ProductSpecificationValue;
import com.zhengcheng.mall.domain.mapper.ProductSkuMapper;
import com.zhengcheng.mall.domain.mapper.ProductSpecificationValueMapper;

import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.text.StrBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * JsonTest
 *
 * @author quansheng1.zhang
 * @since 2022/5/12 10:01
 */
@Slf4j
public class JsonTest extends BaseTest {

    @Autowired
    private ProductSkuMapper                productSkuMapper;
    @Autowired
    private ProductSpecificationValueMapper productSpecificationValueMapper;

    @Test
    public void twoAttrToJson() {

        JSONObject jsonObject = new JSONObject();

        BeanDesc desc = BeanUtil.getBeanDesc(ProductSku.class);

        List<ProductSku> productSkus = productSkuMapper.selectList(null);
        List<ProductSpecificationValue> allProductSpecificationValues = productSpecificationValueMapper.selectList(
                new LambdaQueryWrapper<ProductSpecificationValue>().in(ProductSpecificationValue::getProductSkuId,
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
                log.info(sb2 + "-------->" + BeanUtil.getFieldValue(productSku, propDesc.getField().getName()));
                try {
                    jsonObject.put(sb2.toString(), BeanUtil.getFieldValue(productSku, propDesc.getField().getName()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        log.info(jsonObject.toString());
    }
}
