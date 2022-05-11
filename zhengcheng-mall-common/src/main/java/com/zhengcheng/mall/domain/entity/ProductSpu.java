package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * ProductSpu
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 18:30
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("product_spu")
public class ProductSpu extends BaseEntity<ProductSpu> {

    private static final long serialVersionUID = 5259164221841220994L;

    /** 编号 */
    private String            spuNo;

    /** 名称 */
    private String            name;

    /** 展示图片 */
    private String            image;

    /** 单位 */
    private String            unit;

    /** 重量 */
    private Integer           weight;

    /** 是否上架 */
    @TableField(value = "is_marketable")
    private Boolean           marketable;

    /** 是否置顶 */
    @TableField(value = "is_top")
    private Boolean           top;

    /** 介绍 */
    private String            introduction;

    /** 备注 */
    private String            memo;

    /** 搜索关键词 */
    private String            keyword;

    /** 商品分类 */
    private Long              productCategoryId;

    /** 品牌 */
    private Long              brandId;
}
