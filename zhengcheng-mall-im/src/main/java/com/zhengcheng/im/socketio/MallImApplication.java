package com.zhengcheng.im.socketio;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import com.zhengcheng.im.socketio.common.properties.NettySocketProperties;

import cn.hutool.core.util.StrUtil;

/**
 * MallImApplication
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 22:37
 */
@EnableConfigurationProperties({ NettySocketProperties.class })
@SpringBootApplication
public class MallImApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallImApplication.class).run(args);
    }

    @Bean
    public SocketIOServer socketIOServer(NettySocketProperties nettySocketProperties) {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setOrigin(nettySocketProperties.getOrigin());
        config.setHostname(nettySocketProperties.getHost());
        config.setPort(nettySocketProperties.getPort());
        if (nettySocketProperties.getRedisson().isEnable()) {
            org.redisson.config.Config redissonConfig = new org.redisson.config.Config();
            // 去官网看集群版 https://github.com/redisson/redisson#quick-start
            String address = "redis://".concat(nettySocketProperties.getRedisson().getHost()).concat(":")
                    .concat(nettySocketProperties.getRedisson().getPort());
            redissonConfig.useSingleServer().setPassword(nettySocketProperties.getRedisson().getPassword())
                    .setAddress(address);
            RedissonClient redisson = Redisson.create(redissonConfig);
            RedissonStoreFactory redisStoreFactory = new RedissonStoreFactory(redisson);
            config.setStoreFactory(redisStoreFactory);
        }
        config.setRandomSession(nettySocketProperties.isRandomSession());
        config.setPingInterval(nettySocketProperties.getPingInterval());
        config.setUpgradeTimeout(nettySocketProperties.getUpgradeTimeout());
        config.setPingTimeout(nettySocketProperties.getPingTimeout());
        config.setAuthorizationListener(data -> {
            String tokenValue = data.getSingleUrlParam(nettySocketProperties.getTokenName());
            return StrUtil.equals(nettySocketProperties.getTokenValue(), tokenValue);
        });
        return new SocketIOServer(config);
    }

    /**
     * 用于扫描netty-socketio的注解，比如 @OnConnect、@OnEvent
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }

}
