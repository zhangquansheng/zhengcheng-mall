package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 规格
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 13:29
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("specification")
public class Specification extends BaseEntity<Specification> {

    private static final long serialVersionUID = -3399494417843439107L;

    /**
     * 商品分类ID
     */
    private Long              productCategoryId;

    /** 名称 */
    private String            name;

    /** 备注 */
    private String            memo;
}
