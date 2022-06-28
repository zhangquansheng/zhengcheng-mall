package com.zhengcheng.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zhengcheng.common.web.Result;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;

/**
 * [Sa-Token 权限认证] 配置类 
 *
 * @author quansheng1.zhang
 * @since 2022/5/28 18:20
 */
@Configuration
public class SaTokenConfigure {
    // 注册 Sa-Token全局过滤器 
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址 
                .addInclude("/**")
                // 开放地址 
                .addExclude("/favicon.ico", "/static/**", "/swagger-ui.html", "/doc.html", "/webjars/**",
                        "/swagger-resources", "/swagger-resources/**", "/**/v2/api-docs")
                // 鉴权方法：每次访问进入 
                .setAuth(obj -> {
                    // 登录校验 -- 拦截所有路由，并排除 /oauth/token 用于开放登录 
                    // StpUtil.checkLogin() 要求网关服务同授权登录服务共用一个redis缓存
                    SaRouter.match("/**", "/**/wx/user/**/login", r -> StpUtil.checkLogin());
                })
                // 异常处理方法：每次setAuth函数出现异常时进入 
                .setError(e -> {
                    // https://toscode.gitee.com/dromara/sa-token/issues/I46ZZF
                    SaHolder.getResponse().setHeader("Content-Type", "application/json");
                    return JSONUtil.parse(Result.errorMessage(e.getMessage()));
                });
    }
}
