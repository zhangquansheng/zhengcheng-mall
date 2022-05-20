package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.Payment;
import com.zhengcheng.mall.domain.mapper.PaymentMapper;
import com.zhengcheng.mall.service.PaymentService;

/**
 * PaymentServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/20 11:05
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
}
