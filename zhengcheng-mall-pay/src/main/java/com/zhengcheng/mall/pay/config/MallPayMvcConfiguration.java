package com.zhengcheng.mall.pay.config;

import com.zhengcheng.mall.pay.interceptor.AliPayInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MallAdminMvcConfiguration
 *
 * @author :    zhangquansheng
 * @date :    2020/1/8 11:16
 */
@Configuration
public class MallPayMvcConfiguration implements WebMvcConfigurer {

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AliPayInterceptor()).addPathPatterns("/aliPay/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
