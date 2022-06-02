package com.zhengcheng.mall.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengcheng.mall.bbs.domain.dao.JieCategoryDao;
import com.zhengcheng.mall.bbs.domain.entity.JieCategory;
import com.zhengcheng.mall.bbs.service.JieCategoryService;

/**
 * Service - 帖子专栏
 *
 * @author zqs
 * @version 3.0
 */
@Service
//@CacheConfig(cacheNames = "bbs:jieCategories:")
public class JieCategoryServiceImpl extends BaseServiceImpl<JieCategory, Long> implements JieCategoryService {

    @Autowired
    public void setBaseDao(JieCategoryDao jieCategoryDao) {
        super.setBaseDao(jieCategoryDao);
    }

    //    @Cacheable(key = "#p0")
    @Override
    public JieCategory find(Long id) {
        return super.find(id);
    }

    //    @Cacheable
    @Override
    public List<JieCategory> findAll() {
        return super.findAll();
    }
}
