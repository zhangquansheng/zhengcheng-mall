package com.zhengcheng.mall.user.gatewayimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhengcheng.mall.user.gateway.UserGateway;
import com.zhengcheng.mall.user.mapper.UserMapper;
import com.zhengcheng.mall.user.model.User;

/**
 * UserGatewayImpl
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 15:36
 */
@Component
public class UserGatewayImpl implements UserGateway {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUserNo(String userNo) {
        return userMapper.;
    }
}
