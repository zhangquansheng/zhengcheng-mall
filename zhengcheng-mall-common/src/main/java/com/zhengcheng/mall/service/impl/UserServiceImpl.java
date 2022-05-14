package com.zhengcheng.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.User;
import com.zhengcheng.mall.domain.mapper.UserMapper;
import com.zhengcheng.mall.service.UserService;

import cn.hutool.core.util.RandomUtil;

/**
 * 用户(User)表服务实现类
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户编号随机长度
     */
    public final int   USER_NO_RANDOM_LENGTH = 6;

    @Override
    public boolean save(User user) {
        user.setUserNo(RandomUtil.randomString(USER_NO_RANDOM_LENGTH));
        return userMapper.insert(user) > 0;
    }

}
