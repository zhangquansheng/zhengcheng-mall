package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.SocialUser;
import com.zhengcheng.mall.domain.mapper.SocialUserMapper;
import com.zhengcheng.mall.service.SocialUserService;

/**
 * 社会化用户表(SocialUser)表服务实现类
 *
 * @author quansheng1.zhang
 * @since 2021-08-14 16:57:02
 */
@Service
public class SocialUserServiceImpl extends ServiceImpl<SocialUserMapper, SocialUser> implements SocialUserService {

}
