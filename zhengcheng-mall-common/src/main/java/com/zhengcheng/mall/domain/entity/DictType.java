package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 数据字典类型
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 20:22
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dict_type")
public class DictType extends BaseEntity<DictType> {
    private static final long serialVersionUID = -4183266293032891930L;

    /**
     * 名称
     */
    private String            name;
    /**
     * 编码
     */
    private String            code;
    /**
     * 描述
     */
    private String            description;
    /**
     * 是否启用
     */
    @TableField(value = "is_enable")
    private Boolean           enable;
    /**
     * 备注
     */
    private String            remark;
    /**
     * 参数
     */
    private String            params;
}
