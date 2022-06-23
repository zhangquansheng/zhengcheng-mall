package com.zhengcheng.mall.order.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhengcheng.mall.dto.OrderCreateCmd;
import com.zhengcheng.mall.dto.data.OrderDTO;
import com.zhengcheng.mall.order.gateway.OrderGateway;
import com.zhengcheng.mall.user.gateway.UserGateway;
import com.zhengcheng.mall.user.model.User;

/**
 * OrderCreateCmdExe
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 10:36
 */
@Component
public class OrderCreateCmdExe {

    @Autowired
    private UserGateway  userGateway;
    @Autowired
    private OrderGateway orderGateway;

    public OrderDTO execute(OrderCreateCmd orderCreateCmd) {
        //The flow of usecase is defined here.
        //The core ablility should be implemented in Domain. or sink to Domian gradually
        User user = userGateway.getByUserNo(orderCreateCmd.getUserNo());
        return null;
    }

}
