package com.zhengcheng.mall.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zhengcheng.mall.common.interceptor.LoginInterceptor;

/**
 * MallAdminMvcConfiguration
 *
 * @author :    zhangquansheng
 * @date :    2020/1/8 11:16
 */
@Configuration
public class MallAdminMvcConfiguration implements WebMvcConfigurer {

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login",
                "/login/**", "/reg", "/reg/**", "/oauth/**", "/static/**", "/swagger-ui.html", "/doc.html",
                "/webjars/**", "/swagger-resources", "/swagger-resources/**", "/v2/api-docs");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
