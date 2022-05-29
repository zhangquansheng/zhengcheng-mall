package com.zhengcheng.mall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * MallDDDApplication
 *
 * @author quansheng1.zhang
 * @since 2022/5/29 11:18
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MallDDDApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallDDDApplication.class).run(args);
    }

}
