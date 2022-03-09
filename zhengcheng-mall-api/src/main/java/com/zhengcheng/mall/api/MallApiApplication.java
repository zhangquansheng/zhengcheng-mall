package com.zhengcheng.mall.api;

import org.springframework.boot.builder.SpringApplicationBuilder;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.zhengcheng.core.annotation.EnableZhengChengService;

/**
 * MallApiApplication
 *
 * @author quansheng1.zhang
 * @since 2022/3/8 17:36
 */
@EnableZhengChengService
@NacosPropertySource(dataId = "zhengcheng-mall", autoRefreshed = true)
public class MallApiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallApiApplication.class).run(args);
    }

}
