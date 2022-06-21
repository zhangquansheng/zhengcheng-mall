package com.zhengcheng.mall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Spring Boot Starter
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 14:04
 */
@SpringBootApplication
public class ColaAppliaction {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ColaAppliaction.class).run(args);
    }
}
