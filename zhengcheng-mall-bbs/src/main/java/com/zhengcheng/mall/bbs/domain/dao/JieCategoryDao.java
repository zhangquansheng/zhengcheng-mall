package com.zhengcheng.mall.bbs.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhengcheng.mall.bbs.domain.entity.JieCategory;

/**
 * Dao - 帖子专栏
 *
 * @author zqs
 * @version 3.0
 */
public interface JieCategoryDao extends JpaRepository<JieCategory, Long> {

}
