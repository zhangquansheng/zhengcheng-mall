package com.zhengcheng.mall.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * MallApiApplication
 *
 * @author quansheng1.zhang
 * @since 2022/3/8 17:36
 */
@EnableDiscoveryClient
@ComponentScan(basePackages ="com.zhengcheng.mall")
@SpringBootApplication
public class MallApiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallApiApplication.class).run(args);
    }

}
