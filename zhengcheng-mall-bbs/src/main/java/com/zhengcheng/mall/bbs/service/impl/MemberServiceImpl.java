package com.zhengcheng.mall.bbs.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhengcheng.mall.bbs.common.constant.BBSContant;
import com.zhengcheng.mall.bbs.domain.dao.MemberDao;
import com.zhengcheng.mall.bbs.domain.dao.MessageDao;
import com.zhengcheng.mall.bbs.domain.entity.BbsMessage;
import com.zhengcheng.mall.bbs.domain.entity.Member;
import com.zhengcheng.mall.bbs.domain.enums.Gender;
import com.zhengcheng.mall.bbs.service.MemberService;

import cn.hutool.core.date.DateUtil;

/**
 * 会员
 *
 * @author zqs
 * @version 3.0
 */
@CacheConfig(cacheNames = "bbs:member:")
@Service
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements MemberService {

    @Autowired
    private MemberDao  memberDao;
    @Autowired
    private MessageDao messageDao;

    @Autowired
    public void setBaseDao(MemberDao memberDao) {
        super.setBaseDao(memberDao);
    }

    @Override
    public boolean usernameExists(String username) {
        if (findByUsername(username) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean nicknameExists(String nickname) {
        if (memberDao.findByNickname(nickname) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean usernameDisabled(String username) {
        String[] disabledUsernames = new String[] { "admin", "zskj", "zqs", "paul" };
        for (String disabledUsername : disabledUsernames) {
            if (StringUtils.containsIgnoreCase(username, disabledUsername)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Member findByUsername(String username) {
        return memberDao.findByUsername(username);
    }

    @Override
    public Member findByNickname(String nickname) {
        return memberDao.findByNickname(nickname);
    }

    /**
     * 获取当前登陆信息
     *
     * @return
     */
    private Principal getCurrentPrincipal() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
            return principal;
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isAuthenticated() {
        Principal principal = getCurrentPrincipal();
        if (principal != null) {
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    @Override
    public Member getCurrent() {
        Principal principal = getCurrentPrincipal();
        if (principal != null) {
            return super.find(principal.getId());
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getCurrentID() {
        Principal principal = getCurrentPrincipal();
        if (principal != null) {
            return principal.getId();
        }
        return null;
    }

    @Transactional
    @Override
    public Member register(String username, String nickname, String password) {

        Member member = new Member();
        member.setUsername(username.toLowerCase());
        member.setNickname(nickname);
        member.setPassword(DigestUtils.md5Hex(password));
        member.setEnable(true);
        member.setAdmin(false);
        member.setLight(false);
        member.setJieAuth(true);
        member.setReplyAuth(true);
        member.setGender(Gender.male);
        member.setCertified("社区羊");
        member.setKiss(0L);
        member.setReplies(0L);
        member.setWeekReplies(0L);
        member.setSignLastKiss(0L);
        member.setSignNonstopCount(0L);
        member.setSignTotalCount(0L);
        memberDao.save(member);

        BbsMessage bbsMessage = new BbsMessage();
        bbsMessage.setContent("系统消息：欢迎使用 " + BBSContant.SITE_NAME);
        bbsMessage.setMember(member);
        bbsMessage.setReceiverRead(false);
        messageDao.save(bbsMessage);
        return member;
    }

    @Override
    public Page<Member> findPage(Long weekReplies, Boolean enable, Pageable pageable) {
        Page<Member> page = memberDao.findAll(new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                Predicate[] p = new Predicate[list.size()];
                if (null != weekReplies) {
                    list.add(criteriaBuilder.gt(root.<Long> get("weekReplies"), weekReplies));
                }
                if (null != enable) {
                    list.add(criteriaBuilder.equal(root.<Long> get("enable"), enable));
                }
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return page;
    }

    @Override
    public Page<Member> findPageBySignTime(Pageable pageable) {
        Page<Member> page = memberDao.findAll(new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                Predicate[] p = new Predicate[list.size()];
                Date beginTime = DateUtil.parse(DateUtil.format(new Date(), "yyyy-MM-dd") + " 00:00:00",
                        "yyyy-MM-dd HH:mm:ss");
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("signLastTime"), beginTime));
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return page;
    }

    @Override
    public Page<Member> findPageBySigncount(Pageable pageable) {
        Page<Member> page = memberDao.findAll(new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                Predicate[] p = new Predicate[list.size()];
                list.add(criteriaBuilder.gt(root.<Long> get("signNonstopCount"), 0L));
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return page;
    }

    @Override
    public Boolean isSign(Member member) {
        Date nowDate = new Date();
        try {
            Date beginTime = DateUtil.parse(DateUtil.format(nowDate, "yyyy-MM-dd") + " 00:00:00",
                    "yyyy-MM-dd HH:mm:ss");
            Date endTime = DateUtil.parse(DateUtil.format(nowDate, "yyyy-MM-dd") + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            if (DateUtils.belongCalendar(member.getSignLastTime(), beginTime, endTime)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @CacheEvict(key = "#p0.id")
    @Transactional
    @Override
    public Map<String, Object> sign(Member member) {
        Long days = member.getSignTotalCount() + 1L;
        Long kiss = 5L;
        member.setSignLastKiss(kiss);
        member.setSignTotalCount(member.getSignTotalCount() + 1);
        member.setSignNonstopCount(days);
        member.setSignLastTime(new Date());
        member.setKiss(member.getKiss() + kiss);
        memberDao.save(member);

        Map<String, Object> data = new HashMap<>();
        data.put("signed", true);
        data.put("experience", kiss);
        data.put("days", days);
        return data;
    }

    @CacheEvict(key = "#p0.id")
    @Transactional
    @Override
    public boolean update(Member currentMember, Long id, String field, String value) {
        try {
            if (!currentMember.getAdmin()) {
                return false;
            }
            Member member = memberDao.findMemberByIdForUpdate(id);
            if ("enable".equals(field) || "admin".equals(field) || "jieAuth".equals(field) || "replyAuth".equals(field)
                    || "light".equals(field)) {
                PropertyUtils.setProperty(member, field, Boolean.valueOf(value));
            } else if ("kiss".equals(field)) {
                PropertyUtils.setProperty(member, field, Long.valueOf(value));
            } else {
                if (StringUtils.isEmpty(value)) {
                    PropertyUtils.setProperty(member, field, null);
                } else {
                    PropertyUtils.setProperty(member, field, value);
                }

            }
            memberDao.saveAndFlush(member);
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    @CacheEvict(key = "#p0")
    @Override
    public void updateAvatar(Long id, String avatar) {
        Member member = memberDao.findMemberByIdForUpdate(id);
        if (StringUtils.isEmpty(member.getAvatar())) {
            member.setKiss(member.getKiss() + BBSContant.FIRST_GIVE_KISS);
        }
        member.setAvatar(avatar);
        memberDao.saveAndFlush(member);
    }

    @Cacheable(key = "#p0")
    @Override
    public Member find(Long id) {
        return super.find(id);
    }
}
