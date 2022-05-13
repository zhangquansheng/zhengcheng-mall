package com.zhengcheng.mall.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 商城的配置
 *
 * @author quansheng1.zhang
 * @since 2022/3/9 14:50
 */
@RefreshScope
@Component
@ConfigurationProperties(prefix = "mall")
@Data
public class MallProperties {

    private int     userNoRandomLength  = 6;

    private String  userPasswordMd5Sign = "zc-mall-user";

    private Integer authorityMaxLevel   = 3;
}
