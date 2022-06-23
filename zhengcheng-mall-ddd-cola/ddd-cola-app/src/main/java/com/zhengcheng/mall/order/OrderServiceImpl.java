package com.zhengcheng.mall.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.zhengcheng.mall.api.OrderService;
import com.zhengcheng.mall.dto.OrderCreateCmd;
import com.zhengcheng.mall.dto.data.OrderDTO;
import com.zhengcheng.mall.order.executor.OrderCreateCmdExe;

/**
 * OrderServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/6/23 10:10
 */
@Service
@CatchAndLog
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderCreateCmdExe orderCreateCmdExe;

    @Override
    public OrderDTO createOrder(OrderCreateCmd orderCreateCmd) {
        return orderCreateCmdExe.execute(orderCreateCmd);
    }
}
