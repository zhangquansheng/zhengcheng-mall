package com.zhengcheng.mall.user.gateway;

import com.zhengcheng.mall.user.model.User;

/**
 * UserGateway
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 15:26
 */
public interface UserGateway {
    User getByUserNo(String userNo);
}
