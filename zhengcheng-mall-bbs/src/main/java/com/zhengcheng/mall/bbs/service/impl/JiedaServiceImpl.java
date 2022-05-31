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
import org.springframework.transaction.annotation.Transactional;

import com.zhengcheng.mall.bbs.domain.dao.JieDao;
import com.zhengcheng.mall.bbs.domain.dao.JiedaDao;
import com.zhengcheng.mall.bbs.domain.dao.MemberDao;
import com.zhengcheng.mall.bbs.domain.dao.MessageDao;
import com.zhengcheng.mall.bbs.domain.entity.BbsMessage;
import com.zhengcheng.mall.bbs.domain.entity.Jie;
import com.zhengcheng.mall.bbs.domain.entity.Jieda;
import com.zhengcheng.mall.bbs.domain.entity.Member;
import com.zhengcheng.mall.bbs.service.JiedaService;

/**
 * Service - 回帖
 *
 * @author zqs
 * @version 3.0
 */
@Service
public class JiedaServiceImpl extends BaseServiceImpl<Jieda, Long> implements JiedaService {

    @Autowired
    private JiedaDao   jiedaDao;
    @Autowired
    private MemberDao  memberDao;
    @Autowired
    private JieDao     jieDao;
    @Autowired
    private MessageDao messageDao;

    @Autowired
    public void setBaseDao(JiedaDao jiedaDao) {
        super.setBaseDao(jiedaDao);
    }

    @Transactional
    @Override
    public boolean accept(Long memberId, Long id) {
        Jieda jieda = jiedaDao.findJiedaByIdForUpdate(id);
        Jie jie = jieda.getJie();
        /**
         * 只有发帖人才能采纳
         */
        if (!jie.getMember().getId().equals(memberId)) {
            return false;
        }
        /**
         * 只能采纳别人的回帖
         */
        if (jie.getMember().equals(jieda.getMember())) {
            return false;
        }

        jieda.setCaina(true);
        jiedaDao.saveAndFlush(jieda);

        jie.setFinished(true);
        jieDao.saveAndFlush(jie);

        Member member = memberDao.findMemberByIdForUpdate(jieda.getMember().getId());
        member.setKiss(member.getKiss() + jie.getExperience());
        memberDao.saveAndFlush(member);

        BbsMessage message = new BbsMessage();
        message.setReceiverRead(false);
        message.setMember(member);
        message.setContent(
                "<a href=\"/user/jump?nickname=" + jie.getMember().getNickname() + "\" target=\"_blank\"><cite>"
                        + jie.getMember().getNickname() + "</cite></a>在<a target=\"_blank\" href=\"/jie/detail/"
                        + jieda.getJie().getId() + "#item-" + jieda.getId() + "\"><cite>" + jieda.getJie().getTitle()
                        + "</cite></a>采纳了你的回复，你的积分+" + jie.getExperience() + ",积分余额为" + member.getKiss());
        messageDao.save(message);
        return true;
    }

    @Override
    public Page<Jieda> findPage(Member member, Pageable pageable) {
        return jiedaDao.findAll(new Specification<Jieda>() {
            @Override
            public Predicate toPredicate(Root<Jieda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != member) {
                    list.add(criteriaBuilder.equal(root.get("member"), member));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
    }
}
