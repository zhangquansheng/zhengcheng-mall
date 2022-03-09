package com.zhengcheng.mall.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

/**
 * MallAdminApplication
 *
 * @author quansheng1.zhang
 * @since 2022/3/9 17:34
 */
@ComponentScan(basePackages ="com.zhengcheng.mall")
@SpringBootApplication
@NacosPropertySource(dataId = "zhengcheng-mall", autoRefreshed = true)
public class MallAdminApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallAdminApplication.class).run(args);
    }

}
