package com.zhengcheng.mall.bbs.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zhengcheng.mall.bbs.common.constant.BBSContant;
import com.zhengcheng.mall.bbs.domain.enums.JieOrderAttr;
import com.zhengcheng.mall.bbs.domain.enums.JieType;

/**
 * MallBbsMvcConfiguration
 *
 * @author :    zhangquansheng
 * @date :    2020/1/8 11:16
 */
@Configuration
public class MallBbsMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName(
                "forward:/jie/" + BBSContant.ALL_JIE_CATEGORY_ID + "/" + JieType.colligate + "/" + JieOrderAttr.newest);
    }
}
