package com.zhengcheng.mall;

/**
 * Spring Boot Starter
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 14:04
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ColaAppliaction {

    public static void main(String[] args) {
        ColaAppliaction.run(ColaAppliaction.class, args);
    }
}
