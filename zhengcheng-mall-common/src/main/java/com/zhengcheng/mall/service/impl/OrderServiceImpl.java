package com.zhengcheng.mall.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.Order;
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
        order.setShippingStatus("unshipped");
        order.setFee(new BigDecimal(0));
        order.setPromotionDiscount(new BigDecimal(0));
        order.setCouponDiscount(new BigDecimal(0));
        order.setOffsetAmount(new BigDecimal(0));
        order.setPoint(0L);
        order.setMemo(memo);
        order.setUserId(userId);
        order.setFreight(new BigDecimal(0));
        order.setInvoice(false);
        order.setTax(new BigDecimal(0));
        order.setAmountPaid(new BigDecimal(0));

        order.setOrderStatus("unconfirmed");
        order.setPaymentStatus("unpaid");

        order.setExpire(LocalDateTimeUtil.offset(LocalDateTime.now(), 60, ChronoUnit.MINUTES));

        order.setOrderNo(orderNo);
        order.setAllocatedStock(true);

        orderMapper.insert(order);

        return order;
    }
}
