package com.zhengcheng.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * MallMonitorApplication
 *
 * @author quansheng1.zhang
 * @since 2022/6/13 22:37
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class MallMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallMonitorApplication.class, args);
    }

}
