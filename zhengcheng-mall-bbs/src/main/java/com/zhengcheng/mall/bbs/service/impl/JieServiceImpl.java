package com.zhengcheng.mall.bbs.service.impl;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhengcheng.mall.bbs.common.constant.BBSContant;
import com.zhengcheng.mall.bbs.domain.dao.JieDao;
import com.zhengcheng.mall.bbs.domain.dao.JiedaDao;
import com.zhengcheng.mall.bbs.domain.dao.MemberDao;
import com.zhengcheng.mall.bbs.domain.dao.MessageDao;
import com.zhengcheng.mall.bbs.domain.entity.*;
import com.zhengcheng.mall.bbs.domain.enums.AuditStatus;
import com.zhengcheng.mall.bbs.service.JieService;

import cn.hutool.core.date.DateUtil;

/**
 * Service -  帖子
 *
 * @author zqs
 * @version 3.0
 */
@Service
public class JieServiceImpl extends BaseServiceImpl<Jie, Long> implements JieService {

    @Autowired
    private JieDao     jieDao;
    @Autowired
    private MemberDao  memberDao;
    @Autowired
    private JiedaDao   jiedaDao;
    @Autowired
    private MessageDao messageDao;

    @Autowired
    public void setBaseDao(JieDao jieDao) {
        super.setBaseDao(jieDao);
    }

    @Override
    public Page<Jie> findPage(Boolean top, Boolean good, Boolean finished, AuditStatus auditStatus,
                              JieCategory category, Member member, Member favoriteMember, Pageable pageable) {
        return jieDao.findAll(new Specification<Jie>() {
            @Override
            public Predicate toPredicate(Root<Jie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != top) {
                    list.add(criteriaBuilder.equal(root.get("top"), top));
                }
                if (null != good) {
                    list.add(criteriaBuilder.equal(root.get("good"), good));
                }
                if (null != finished) {
                    list.add(criteriaBuilder.equal(root.get("finished"), finished));
                }
                if (null != auditStatus) {
                    list.add(criteriaBuilder.equal(root.get("auditStatus"), auditStatus));
                }
                if (null != category) {
                    list.add(criteriaBuilder.equal(root.get("category"), category));
                }
                if (null != member) {
                    list.add(criteriaBuilder.equal(root.get("member"), member));
                }
                if (null != favoriteMember) {
                    list.add(criteriaBuilder.equal(root.join("favoriteMembers"), favoriteMember));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
    }

    @Override
    public Page<Jie> findPage(Date beginDate, Long commentNums, AuditStatus auditStatus, Pageable pageable) {
        return jieDao.findAll(new Specification<Jie>() {
            @Override
            public Predicate toPredicate(Root<Jie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != beginDate) {
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("createDate"), beginDate));
                }
                if (null != commentNums) {
                    list.add(criteriaBuilder.gt(root.<Long> get("commentNums"), commentNums));
                }
                if (null != auditStatus) {
                    list.add(criteriaBuilder.equal(root.get("auditStatus"), auditStatus));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
    }

    @Transactional
    @Override
    public Map<String, Object> post(Jie jie, Long memberId) {
        Map<String, Object> result = new HashMap<>();
        if (memberId == null) {
            result.put("success", false);
            result.put("errorMsg", "memberId is null");
            return result;
        }
        Member member = memberDao.findMemberByIdForUpdate(memberId);
        if (!member.getJieAuth()) {
            result.put("success", false);
            result.put("errorMsg", "此账号无权限发布帖子！");
            return result;
        }
        if (!Arrays.asList(BBSContant.KISS_LIST).contains(jie.getExperience())) {
            result.put("success", false);
            result.put("errorMsg", "请选择正确的悬赏飞吻");
            return result;
        }
        if (member.getKiss() < jie.getExperience()) {
            result.put("success", false);
            result.put("errorMsg", "你的飞吻不足！可以去签到、回帖获得");
            return result;
        }
        member.setKiss(member.getKiss() - jie.getExperience());
        memberDao.saveAndFlush(member);

        jie.setMember(member);
        jie.setAuditStatus(AuditStatus.handing);
        jie.setTop(false);
        jie.setFinished(false);
        jie.setGood(false);
        jie.setCommentNums(0L);
        jie.setHits(0L);
        jieDao.save(jie);
        result.put("success", true);
        return result;
    }

    @Transactional
    @Override
    public Map<String, Object> reply(Long jid, Long memberId, String content) {

        Map<String, Object> result = new HashMap<>();
        Jie jie = jieDao.findJieByIdForUpdate(jid); //需要加锁

        if (!jie.getAuditStatus().equals(AuditStatus.adopt)) {
            result.put("success", false);
            result.put("errorMsg", "帖子正在审核中，不能回帖！");
            return result;
        }

        Member member = memberDao.findMemberByIdForUpdate(memberId);
        if (!member.getReplyAuth()) {
            result.put("success", false);
            result.put("errorMsg", "你的回帖权限已被管理员限制，请稍后重试！");
            return result;
        }
        /**
         * 没有头像不能上传
         */
        if (StringUtils.isEmpty(member.getAvatar())) {
            result.put("success", false);
            result.put("errorMsg", "首次回帖需要你上传头像，请去个人中心设置！");
            return result;
        }
        Date nowDate = new Date();
        Date weekMonday = DateUtil.beginOfWeek(nowDate);
        String lastReplyWeek = "";
        if (member.getLastReplyWeek() != null) {
            lastReplyWeek = DateUtil.format(member.getLastReplyWeek(), "yyyy-MM-dd");
        }
        if (DateUtil.format(weekMonday, "yyyy-MM-dd").equals(lastReplyWeek)) {
            member.setWeekReplies(member.getWeekReplies() + 1L);
        } else {
            member.setWeekReplies(1L);
            member.setLastReplyWeek(weekMonday);
        }
        member.setReplies(member.getReplies() + 1L);
        memberDao.saveAndFlush(member);

        boolean aitMessage = false;
        Member aitMember = null;

        Jieda jieda = new Jieda();
        jieda.setZan(0L);
        jieda.setCaina(false);
        jieda.setContent(content);
        jieda.setJie(jie);
        jieda.setMember(member);
        jieda.setZanMembers(null);

        if (content.startsWith("@")) {
            int startIndex = content.indexOf(" ");
            if (startIndex > -1) {
                String nickname = content.substring(1, startIndex);
                aitMember = memberDao.findByNickname(nickname);
                if (aitMember != null) {
                    jieda.getAitMembers().add(aitMember);
                    if (!member.equals(aitMember) && !aitMember.equals(jie.getMember())) {
                        aitMessage = true;
                    }
                }
            }
        }
        jiedaDao.save(jieda);

        jie.setCommentNums(jie.getCommentNums() + 1L);
        jieDao.save(jie);

        /**
         * 发送艾特的会员消息
         */
        if (aitMessage) {
            BbsMessage message = new BbsMessage();
            message.setContent("<a href=\"/user/jump?nickname=" + member.getNickname() + "\" target=\"_blank\"><cite>"
                    + member.getNickname() + "</cite></a>在<a target=\"_blank\" href=\"/jie/detail/"
                    + jieda.getJie().getId() + "#item-" + jieda.getId() + "\"><cite>" + jieda.getJie().getTitle()
                    + "</cite></a>提到了您！");
            message.setMember(aitMember);
            message.setReceiverRead(false);
            messageDao.save(message);
        }

        /**
         * 自己回复自己的帖子就不发送消息
         */
        if (!member.equals(jie.getMember())) {
            BbsMessage message = new BbsMessage();
            message.setContent("<a href=\"/user/jump?nickname=" + member.getNickname() + "\" target=\"_blank\"><cite>"
                    + member.getNickname() + "</cite></a>回答了您的求解<a target=\"_blank\" href=\"/jie/detail/"
                    + jieda.getJie().getId() + "#item-" + jieda.getId() + "\"><cite>" + jieda.getJie().getTitle()
                    + "</cite></a>");
            message.setMember(jie.getMember());
            message.setReceiverRead(false);
            messageDao.save(message);
        }
        result.put("success", true);
        return result;
    }

}
