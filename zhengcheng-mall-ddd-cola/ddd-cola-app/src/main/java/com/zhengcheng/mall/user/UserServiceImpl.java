package com.zhengcheng.mall.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.zhengcheng.mall.api.UserService;
import com.zhengcheng.mall.dto.UserAddCmd;
import com.zhengcheng.mall.dto.UserByNoQry;
import com.zhengcheng.mall.dto.data.UserDTO;
import com.zhengcheng.mall.user.executor.query.UserByUserNoQryExe;

/**
 * UserServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 10:33
 */
@Service
@CatchAndLog
public class UserServiceImpl implements UserService {

    @Autowired
    private UserByUserNoQryExe userByUserNoQryExe;

    @Override
    public UserDTO getByUserNo(UserByNoQry userByNoQry) {
        return userByUserNoQryExe.execute(userByNoQry);
    }

    @Override
    public Long add(UserAddCmd userAddCmd) {
        return null;
    }
}
