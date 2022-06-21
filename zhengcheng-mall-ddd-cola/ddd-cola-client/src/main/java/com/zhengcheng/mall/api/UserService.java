package com.zhengcheng.mall.api;

import com.zhengcheng.mall.dto.UserAddCmd;
import com.zhengcheng.mall.dto.UserByNoQry;
import com.zhengcheng.mall.dto.data.UserDTO;

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

    /**
     * 根据用户编号查询用户信息
     * @param userByNoQry 用户编号查询条件
     * @return 用户信息
     */
    UserDTO getByUserNo(UserByNoQry userByNoQry);
}
