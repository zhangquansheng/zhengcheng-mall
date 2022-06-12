package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.DouyinUser;
import com.zhengcheng.mall.domain.mapper.DouyinUserMapper;
import com.zhengcheng.mall.service.DouyinUserService;

/**
 * DouyinUserServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/6/12 15:22
 */
@Service
public class DouyinUserServiceImpl extends ServiceImpl<DouyinUserMapper, DouyinUser> implements DouyinUserService {
}
