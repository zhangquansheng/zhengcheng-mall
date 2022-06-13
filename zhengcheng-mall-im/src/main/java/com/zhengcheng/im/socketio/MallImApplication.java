package com.zhengcheng.im.socketio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

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

    // https://github.com/springfox/springfox/issues/3462 解决 Springboot2.6.3不兼容swagger3.0.0问题
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier,
                                                                         ServletEndpointsSupplier servletEndpointsSupplier,
                                                                         ControllerEndpointsSupplier controllerEndpointsSupplier,
                                                                         EndpointMediaTypes endpointMediaTypes,
                                                                         CorsEndpointProperties corsProperties,
                                                                         WebEndpointProperties webEndpointProperties,
                                                                         Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment,
                basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes,
                corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath),
                shouldRegisterLinksMapping, null);
    }

    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment,
                                               String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath)
                || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
