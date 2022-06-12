package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 抖音账号分组
 *
 * @author quansheng1.zhang
 * @since 2022/6/12 15:10
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("douyin_user_group")
public class DouyinUserGroup extends BaseEntity<DouyinUserGroup> {

    private static final long serialVersionUID = 6301482542042018433L;

    /**
     * 分组编号
     */
    private String            groupNo;
    /**
     * 分组名称
     */
    private String            name;
}
