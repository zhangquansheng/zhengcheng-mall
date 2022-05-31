package com.zhengcheng.mall.bbs.service;

import java.util.Date;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zhengcheng.mall.bbs.domain.entity.Jie;
import com.zhengcheng.mall.bbs.domain.entity.JieCategory;
import com.zhengcheng.mall.bbs.domain.entity.Member;
import com.zhengcheng.mall.bbs.domain.enums.AuditStatus;

/**
 * Service - 帖子
 *
 * @author zqs
 * @version 3.0
 */
public interface JieService extends BaseService<Jie, Long> {

    /**
     * 分页查询
     *
     * @param top            置顶
     * @param good           精品
     * @param finished       结贴
     * @param auditStatus    审核状态
     * @param category       分类
     * @param member         会员
     * @param favoriteMember 收藏会员
     * @param pageable       分页
     * @return
     */
    Page<Jie> findPage(Boolean top, Boolean good, Boolean finished, AuditStatus auditStatus, JieCategory category,
                       Member member, Member favoriteMember, Pageable pageable);

    /**
     * 根据时间分页查询
     *
     * @param beginDate   开始时间
     * @param commentNums 评论数
     * @param auditStatus 审核状态
     * @param pageable    分页
     * @return
     */
    Page<Jie> findPage(Date beginDate, Long commentNums, AuditStatus auditStatus, Pageable pageable);

    /**
     * 发帖
     *
     * @param jie      帖子
     * @param memberId 会员ID
     * @return
     */
    Map<String, Object> post(Jie jie, Long memberId);

    /**
     * 回帖
     *
     * @param jid      帖子ID
     * @param memberId 会员ID
     * @param content  回帖内容
     * @return
     */
    Map<String, Object> reply(Long jid, Long memberId, String content);
}
