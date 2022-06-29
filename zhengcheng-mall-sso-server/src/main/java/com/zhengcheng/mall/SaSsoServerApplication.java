package com.zhengcheng.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SaSsoServerApplication
 *
 * @author quansheng1.zhang
 * @since 2022/6/29 11:25
 */
@SpringBootApplication
public class SaSsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaSsoServerApplication.class, args);
        System.out.println("\n------ Sa-Token-SSO 认证中心启动成功");
    }
}
