package com.zhengcheng.mall.common.command;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProductSpuDTO
 *
 * @author quansheng1.zhang
 * @since 2022/5/12 20:37
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSpuCommand implements Serializable {
    private static final long       serialVersionUID = 5062390494790183810L;
    /**
     * SPU ID
     */
    private Long                    id;

    /**
     * 商品规格模式 0单规格 1多规格
     */
    private Integer                 specificationMode;
    /**
     * 商品分类
     */
    private Long                    productCategoryId;
    /**
     * 所有的规格值
     */
    private List<Long>              specificationValueIds;
    /**
     * SKU 列表
     */
    private List<ProductSkuCommand> skus;
}
