package com.zhengcheng.mall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.mzt.logapi.starter.annotation.EnableLogRecord;

/**
 * MallAdminApplication
 *
 * @author quansheng1.zhang
 * @since 2022/3/9 17:34
 */
@EnableLogRecord(tenant = "com.zhengcheng.mall.admin")
@EnableFeignClients(basePackages = { "com.zhengcheng.**.feign" })
@EnableDiscoveryClient
@SpringBootApplication
public class MallAdminApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallAdminApplication.class).run(args);
    }

}
