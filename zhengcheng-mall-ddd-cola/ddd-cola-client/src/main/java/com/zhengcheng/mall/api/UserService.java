package com.zhengcheng.mall.api;

import com.zhengcheng.mall.dto.UserAddCmd;

/**
 * UserService
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 10:29
 */
public interface UserService {
    /**
     * 添加会员
     *
     * @param userAddCmd
     *            userAddCmd
     */
    Long add(UserAddCmd userAddCmd);
}
