package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.UserLoginLog;
import com.zhengcheng.mall.domain.mapper.UserLoginLogMapper;
import com.zhengcheng.mall.service.UserLoginLogService;

/**
 * 登录日志表(UserLoginLog)表服务实现类
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 19:49:27
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog>
    implements UserLoginLogService {

}