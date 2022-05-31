package com.zhengcheng.mall.bbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengcheng.mall.bbs.domain.dao.AdDao;
import com.zhengcheng.mall.bbs.domain.entity.Ad;
import com.zhengcheng.mall.bbs.service.AdService;

/**
 * Service - 广告
 *
 * @author zqs
 * @version 3.0
 */
@Service
public class AdServiceImpl extends BaseServiceImpl<Ad, Long> implements AdService {

    @Autowired
    public void setBaseDao(AdDao adDao) {
        super.setBaseDao(adDao);
    }
}
