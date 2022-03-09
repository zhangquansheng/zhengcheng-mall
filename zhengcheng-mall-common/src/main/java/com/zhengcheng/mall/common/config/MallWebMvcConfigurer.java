package com.zhengcheng.mall.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CommonConfig
 *
 * @author quansheng1.zhang
 * @since 2022/3/9 14:27
 */
@Configuration
public class MallWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * 注入BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
