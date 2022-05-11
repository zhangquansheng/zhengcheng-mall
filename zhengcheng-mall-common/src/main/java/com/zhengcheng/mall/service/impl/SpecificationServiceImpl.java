package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.Specification;
import com.zhengcheng.mall.domain.mapper.SpecificationMapper;
import com.zhengcheng.mall.service.SpecificationService;

/**
 * SpecificationServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 13:37
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification>
        implements SpecificationService {
}
