package com.zhengcheng.mall.admin.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zhengcheng.mall.admin.common.interceptor.LoginInterceptor;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;

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
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/**","/login", "/login/**", "/reg", "/reg/**", "/oauth/**",
                "/static/**",
                "/swagger-ui.html", "/doc.html", "/webjars/**", "/swagger-resources", "/swagger-resources/**",
                "/v2/api-docs");
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
