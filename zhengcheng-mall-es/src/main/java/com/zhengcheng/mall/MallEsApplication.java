package com.zhengcheng.mall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * MallEsApplication
 *
 * @author quansheng1.zhang
 * @since 2022/3/8 17:36
 */
@SpringBootApplication
public class MallEsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallEsApplication.class).run(args);
    }

}
