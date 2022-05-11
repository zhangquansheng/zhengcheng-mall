package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * ProductSku
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 18:40
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("product_sku")
public class ProductSku extends BaseEntity<ProductSku> {

    private static final long serialVersionUID = 1811688582245337443L;

    /** 全称 */
    private String            fullName;

    /** 销售价 */
    private Long              price;

    /** 成本价 */
    private Long              cost;

    /** 市场价 */
    private Long              marketPrice;

    /** 库存 */
    private Integer           stock;

    /** 已分配库存 */
    private Integer           allocatedStock;

    /** 库存备注 */
    private String            stockMemo;
    /**
     * 是否启用
     */
    @TableField(value = "is_enable")
    private Boolean           enable;
    /**
     * SPU ID
     */
    private Long              spuId;
    /**
     * SPU 编号
     */
    private String            spuNo;
}
