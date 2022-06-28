package com.zhengcheng.mall.common.command;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加/更新商品SKU
 *
 * @author quansheng1.zhang
 * @since 2022/5/12 19:10
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSkuCommand implements Serializable {

    private static final long serialVersionUID = 3036036161024851066L;

    /**
     * ID
     */
    private Long              id;

    /** 销售价 */
    private Long              price;

    /** 成本价 */
    private Long              cost;

    /** 市场价 */
    private Long              marketPrice;

    /** 库存 */
    private Integer           stock;
    /**
     * 图片地址
     */
    private String            picture;
    /**
     * 是否启用
     */
    private Boolean           enable;
    /**
     * SPU ID
     */
    private Long              spuId;
    /**
     * 规格ID列表
     */
    private List<Long>        specificationIds;
    /**
     * 规格值ID列表
     */
    private List<Long>        specificationValueIds;
}
