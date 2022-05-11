package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 商品规格值
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
@TableName("specification_value")
public class SpecificationValue extends BaseEntity<SpecificationValue> {
    private static final long serialVersionUID = -6670367870719964149L;

    /** 名称 */
    private String            name;

    /** 规格 */
    private Long              specificationId;
}
