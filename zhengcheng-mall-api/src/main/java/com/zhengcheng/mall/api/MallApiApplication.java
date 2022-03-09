package com.zhengcheng.mall.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

/**
 * MallApiApplication
 *
 * @author quansheng1.zhang
 * @since 2022/3/8 17:36
 */
@ComponentScan(basePackages ="com.zhengcheng.mall")
@SpringBootApplication
@NacosPropertySource(dataId = "zhengcheng-mall", autoRefreshed = true)
public class MallApiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallApiApplication.class).run(args);
    }

}
