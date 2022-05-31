package com.zhengcheng.mall.bbs.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zhengcheng.mall.bbs.domain.entity.Ad;

/**
 * Dao - 广告
 *
 * @author zqs
 * @version 3.0
 */
public interface AdDao extends JpaRepository<Ad, Long>, JpaSpecificationExecutor<Ad> {
}
