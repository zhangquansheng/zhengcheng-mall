package com.zhengcheng.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhengcheng.mall.domain.entity.Order;

/**
 * OrderService
 *
 * @author quansheng1.zhang
 * @since 2022/5/8 12:29
 */
public interface OrderService extends IService<Order> {

    Order create(String orderNo, Long userId, String memo);
}
