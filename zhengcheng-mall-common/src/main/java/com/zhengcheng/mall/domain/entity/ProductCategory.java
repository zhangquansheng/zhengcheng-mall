package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 商品分类
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
@TableName("product_category")
public class ProductCategory extends BaseEntity<ProductCategory> {

    private static final long serialVersionUID = 3556975525647320554L;

    /** 名称 */
    private String            name;

    /** 层级 */
    private Integer           grade;

    /** 上级分类 */
    private Long              pid;

}
