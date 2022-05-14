package com.zhengcheng.mall.common.command;

import java.util.List;

import com.zhengcheng.common.web.UserCommand;

import lombok.*;

/**
 * 添加/更新商品SKU
 *
 * @author quansheng1.zhang
 * @since 2022/5/12 19:10
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSkuCommand extends UserCommand {

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
