package com.zhengcheng.mall.user.executor.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhengcheng.mall.dto.UserByNoQry;
import com.zhengcheng.mall.dto.data.UserDTO;
import com.zhengcheng.mall.user.gateway.UserGateway;
import com.zhengcheng.mall.user.model.User;

import cn.hutool.core.bean.BeanUtil;

/**
 * UserByUserNoQryExe
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 15:50
 */
@Component
public class UserByUserNoQryExe {

    @Autowired
    private UserGateway userGateway;

    public UserDTO execute(UserByNoQry cmd) {
        User user = userGateway.getByUserNo(cmd.getUserNo());
        return BeanUtil.copyProperties(user, UserDTO.class);
    }
}
