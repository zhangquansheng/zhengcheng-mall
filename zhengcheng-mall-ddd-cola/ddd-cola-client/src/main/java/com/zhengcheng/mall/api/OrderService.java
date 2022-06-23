package com.zhengcheng.mall.api;

import com.zhengcheng.mall.dto.OrderCreateCmd;
import com.zhengcheng.mall.dto.data.OrderDTO;

/**
 * OrderService
 *
 * @author quansheng1.zhang
 * @since 2022/6/23 10:06
 */
public interface OrderService {
    /**
     * 下单
     * @return OrderDTO
     */
    OrderDTO createOrder(OrderCreateCmd orderCreateCmd);
}
