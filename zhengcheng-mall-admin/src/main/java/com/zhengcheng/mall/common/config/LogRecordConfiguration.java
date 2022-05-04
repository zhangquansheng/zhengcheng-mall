package com.zhengcheng.mall.common.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mzt.logapi.beans.Operator;
import com.mzt.logapi.service.IOperatorGetService;
import com.zhengcheng.mall.common.holder.TokenInfoHolder;

import cn.hutool.core.util.StrUtil;

/**
 * 通用操作日志组件配置
 *
 * @author quansheng1.zhang
 * @since 2022/4/30 18:13
 */
@Configuration
public class LogRecordConfiguration {

    @Bean
    public IOperatorGetService operatorGetService() {
        return () -> Optional.ofNullable(TokenInfoHolder.getTokenInfo())
                .map(a -> new Operator(StrUtil.format("{}({})", a.getLoginType(), a.getLoginDevice())))
                .orElseThrow(() -> new IllegalArgumentException("user is null"));
    }

}
