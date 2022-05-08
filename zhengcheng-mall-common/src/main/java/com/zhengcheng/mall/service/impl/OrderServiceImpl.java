package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.Order;
import com.zhengcheng.mall.domain.mapper.OrderMapper;
import com.zhengcheng.mall.service.OrderService;

/**
 * OrderServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/8 12:30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
