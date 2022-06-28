package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.WxUser;
import com.zhengcheng.mall.domain.mapper.WxUserMapper;
import com.zhengcheng.mall.service.WxUserService;

/**
 * WxUserServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/6/28 16:21
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {

}
