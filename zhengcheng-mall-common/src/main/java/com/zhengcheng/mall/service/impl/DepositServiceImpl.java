package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.Deposit;
import com.zhengcheng.mall.domain.mapper.DepositMapper;
import com.zhengcheng.mall.service.DepositService;

/**
 * DepositServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/19 22:18
 */
@Service
public class DepositServiceImpl extends ServiceImpl<DepositMapper, Deposit> implements DepositService {
}
