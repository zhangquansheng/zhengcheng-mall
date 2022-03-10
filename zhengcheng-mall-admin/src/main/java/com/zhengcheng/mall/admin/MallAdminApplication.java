package com.zhengcheng.mall.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * MallAdminApplication
 *
 * @author quansheng1.zhang
 * @since 2022/3/9 17:34
 */
@ComponentScan(basePackages ="com.zhengcheng.mall")
@EnableFeignClients(basePackages = { "com.zhengcheng.**.feign" })
@EnableDiscoveryClient
@SpringBootApplication
public class MallAdminApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallAdminApplication.class).run(args);
    }

}
