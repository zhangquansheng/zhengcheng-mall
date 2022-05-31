package com.zhengcheng.mall.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.zhengcheng.mall.bbs.domain.dao.FriendLinkDao;
import com.zhengcheng.mall.bbs.domain.entity.FriendLink;
import com.zhengcheng.mall.bbs.service.FriendLinkService;

/**
 * 友情链接
 *
 * @author zqs
 * @version 3.0
 */
@Service
public class FriendLinkServiceImpl extends BaseServiceImpl<FriendLink, Long> implements FriendLinkService {

    @Autowired
    private FriendLinkDao friendLinkDao;

    @Autowired
    public void setBaseDao(FriendLinkDao friendLinkDao) {
        super.setBaseDao(friendLinkDao);
    }

    @Cacheable(value = "bbs:friendLink", key = "#position")
    @Override
    public List<FriendLink> findList(FriendLink.Type type, FriendLink.Position position) {
        return friendLinkDao.findAll(new Specification<FriendLink>() {
            @Override
            public Predicate toPredicate(Root<FriendLink> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != type) {
                    list.add(criteriaBuilder.equal(root.get("type"), type));
                }
                if (null != position) {
                    list.add(criteriaBuilder.equal(root.get("position"), position));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        });
    }
}
