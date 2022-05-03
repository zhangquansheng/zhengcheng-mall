package com.zhengcheng.mall.common.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;

/**
 * FeignInterceptorConfig
 *
 * @author quansheng1.zhang
 * @since 2022/4/21 16:56
 */
@Component
public class FeignInterceptorConfig {

    /**
     * 使用feign client访问别的微服务时，将satoken放入参数或者header
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        RequestInterceptor requestInterceptor = template -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String satoken = request.getHeader("satoken");
                if (StrUtil.isNotBlank(satoken)) {
                    template.header("satoken", satoken);
                }
            }
        };
        return requestInterceptor;
    }
}
