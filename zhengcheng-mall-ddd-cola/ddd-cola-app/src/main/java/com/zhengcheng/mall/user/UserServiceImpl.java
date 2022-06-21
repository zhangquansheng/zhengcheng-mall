package com.zhengcheng.mall.user;

import org.springframework.stereotype.Service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.zhengcheng.mall.api.UserService;
import com.zhengcheng.mall.dto.UserAddCmd;

/**
 * UserServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 10:33
 */
@Service
@CatchAndLog
public class UserServiceImpl implements UserService {

    @Override
    public Long add(UserAddCmd userAddCmd) {
        return null;
    }

}
