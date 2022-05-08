package com.zhengcheng.mall.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.Order;
import com.zhengcheng.mall.domain.enums.OrderStatusEnum;
import com.zhengcheng.mall.domain.enums.PaymentStatusEnum;
import com.zhengcheng.mall.domain.enums.ShippingStatusEnum;
import com.zhengcheng.mall.domain.mapper.OrderMapper;
import com.zhengcheng.mall.service.OrderService;

import cn.hutool.core.date.LocalDateTimeUtil;

/**
 * OrderServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/8 12:30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order create(String orderNo, Long userId, String memo) {
        Order order = new Order();
        order.setCreateUserId(userId);
        order.setUpdateUserId(userId);

        order.setFee(0L);
        order.setPromotionDiscount(0L);
        order.setCouponDiscount(0L);
        order.setOffsetAmount(0L);
        order.setPoint(0L);
        order.setMemo(memo);
        order.setUserId(userId);
        order.setFreight(0L);
        order.setInvoice(false);
        order.setTax(0L);
        order.setAmountPaid(0L);

        order.setOrderStatus(OrderStatusEnum.UNCONFIRMED);
        order.setPaymentStatus(PaymentStatusEnum.UNPAID);
        order.setShippingStatus(ShippingStatusEnum.UNSHIPPED);

        order.setExpire(LocalDateTimeUtil.offset(LocalDateTime.now(), 60, ChronoUnit.MINUTES));

        order.setOrderNo(orderNo);
        order.setAllocatedStock(true);

        orderMapper.insert(order);

        return order;
    }
}
