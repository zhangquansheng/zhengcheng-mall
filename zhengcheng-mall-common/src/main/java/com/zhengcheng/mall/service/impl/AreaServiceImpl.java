package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.Area;
import com.zhengcheng.mall.domain.mapper.AreaMapper;
import com.zhengcheng.mall.service.AreaService;

/**
 * AreaServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/10 11:00
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area> implements AreaService {
}
