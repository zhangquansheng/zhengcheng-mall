package com.zhengcheng.mall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * MallPayApplication
 *
 * @author quansheng1.zhang
 * @since 2022/5/15 00:30
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MallPayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallPayApplication.class).run(args);
    }

}
