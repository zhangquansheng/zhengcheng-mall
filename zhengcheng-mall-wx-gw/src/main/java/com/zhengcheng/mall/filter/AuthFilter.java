package com.zhengcheng.mall.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.zhengcheng.common.constant.CommonConstants;
import com.zhengcheng.common.utils.IpAddressUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 网关鉴权
 *
 * @author quansheng1.zhang
 * @since 2022/6/28 13:38
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) exchange.getRequest();
        ServerHttpRequest mutableReq = exchange.getRequest().mutate()
                .header(CommonConstants.HEADER_CLIENT_IP, IpAddressUtil.getIpAddress(request)).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
