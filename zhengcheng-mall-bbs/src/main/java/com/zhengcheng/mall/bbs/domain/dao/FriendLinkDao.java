package com.zhengcheng.mall.bbs.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zhengcheng.mall.bbs.domain.entity.FriendLink;

/**
 * 友情链接
 *
 * @author zqs
 * @version 3.0
 */
public interface FriendLinkDao extends JpaRepository<FriendLink, Long>, JpaSpecificationExecutor<FriendLink> {
}
