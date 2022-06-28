package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * 微信用户
 *
 * @author quansheng1.zhang
 * @since 2022/6/28 16:17
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wx_user")
public class WxUser extends BaseEntity<WxUser> {

    private static final long serialVersionUID = -2783551528297148416L;

    /**
     * 用户号（user表user_no）
     */
    private String            userNo;
    /**
     * 应用ID
     */
    private String            appid;
    /**
     * 用户唯一标识
     */
    private String            openid;
    /**
     * 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回
     */
    private String            unionid;
}
