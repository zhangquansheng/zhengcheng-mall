package com.zhengcheng.mall.bbs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.zhengcheng.mall.bbs.domain.dao.MessageDao;
import com.zhengcheng.mall.bbs.domain.entity.BbsMessage;
import com.zhengcheng.mall.bbs.domain.entity.Member;
import com.zhengcheng.mall.bbs.service.MessageService;

/**
 * Service - 个人消息
 *
 * @author zqs
 * @version 3.0
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<BbsMessage, Long> implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    public void setBaseDao(MessageDao messageDao) {
        super.setBaseDao(messageDao);
    }

    @Override
    public Page<BbsMessage> findPage(Member member, Pageable pageable) {
        return messageDao.findAll(new Specification<BbsMessage>() {
            @Override
            public Predicate toPredicate(Root<BbsMessage> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != member) {
                    list.add(criteriaBuilder.equal(root.get("member"), member));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
    }

    @Override
    public List<BbsMessage> findList(Member member) {
        return messageDao.findAll(new Specification<BbsMessage>() {
            @Override
            public Predicate toPredicate(Root<BbsMessage> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != member) {
                    list.add(criteriaBuilder.equal(root.get("member"), member));
                }
                query.orderBy(criteriaBuilder.desc(root.get("createDate")));
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        });
    }

    @Override
    public Long count(Boolean receiverRead, Member member) {
        return messageDao.count(new Specification<BbsMessage>() {
            @Override
            public Predicate toPredicate(Root<BbsMessage> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != receiverRead) {
                    list.add(criteriaBuilder.equal(root.get("receiverRead"), receiverRead));
                }
                if (null != member) {
                    list.add(criteriaBuilder.equal(root.get("member"), member));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        });
    }

    @Override
    public void readAll(Member member) {
        messageDao.updateReaded(member);
    }
}
