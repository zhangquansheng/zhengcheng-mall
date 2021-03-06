package com.zhengcheng.mall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

/**
 * MallBbsApplication
 *
 * @author quansheng1.zhang
 * @since 2022/3/8 17:36
 */
@EnableCaching
@SpringBootApplication
public class MallBbsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallBbsApplication.class).run(args);
    }

}
