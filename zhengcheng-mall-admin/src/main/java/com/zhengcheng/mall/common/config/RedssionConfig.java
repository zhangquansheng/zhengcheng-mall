package com.zhengcheng.mall.common.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.hutool.core.util.StrUtil;

/**
 * RedssionConfig
 *
 * @author quansheng1.zhang
 * @since 2022/5/13 12:59
 */
@Configuration
public class RedssionConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * redisson 单机模式自动装配
     */
    @Bean
    RedissonClient redissonSingle() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(StrUtil.format("redis://{}:{}", redisProperties.getHost(), redisProperties.getPort()));

        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            serverConfig.setPassword(redisProperties.getPassword());
        }

        return Redisson.create(config);
    }
}
