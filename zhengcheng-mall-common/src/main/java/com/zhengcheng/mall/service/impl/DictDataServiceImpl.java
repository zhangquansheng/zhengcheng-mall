package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.DictData;
import com.zhengcheng.mall.domain.mapper.DictDataMapper;
import com.zhengcheng.mall.service.DictDataService;

/**
 * DictDataServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/4/29 15:01
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements DictDataService {
}
