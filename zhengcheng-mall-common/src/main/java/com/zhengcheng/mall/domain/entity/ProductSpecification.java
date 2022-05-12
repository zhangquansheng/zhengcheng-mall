package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 商品规格
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 13:30
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("product_specification")
public class ProductSpecification extends BaseEntity<ProductSpecification> {
    private static final long serialVersionUID = -6670367870719964149L;

    /** 商品ID */
    private Long              productSkuId;

    /** 规格ID */
    private Long              specificationId;
}
