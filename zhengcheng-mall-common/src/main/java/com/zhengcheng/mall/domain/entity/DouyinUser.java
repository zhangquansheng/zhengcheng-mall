package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 抖音账号
 *
 * @author quansheng1.zhang
 * @since 2022/6/12 15:12
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("douyin_user")
public class DouyinUser extends BaseEntity<DouyinUser> {

    private static final long serialVersionUID = -2574004704790033944L;
    /**
     * 分组编号
     */
    private String            groupNo;
    /**
     * 用户在当前应用的唯一标识
     */
    private String            openId;
    /**
     * 用户在当前开发者账号下的唯一标识（未绑定开发者账号没有该字段）
     */
    private String            unionId;
    /**
     * 头像
     */
    private String            avatar;
    /**
     * 昵称
     */
    private String            nickname;
    /**
     * 性别: * `0` - 未知 * `1` - 男性 * `2` - 女性
     */
    private Integer           gender;
    /**
     * 国家
     */
    private String            country;
    /**
     * 省份
     */
    private String            province;
    /**
     * 城市
     */
    private String            city;
}
