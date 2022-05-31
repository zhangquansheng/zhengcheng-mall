package com.zhengcheng.mall.bbs.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zhengcheng.mall.bbs.domain.entity.BbsMessage;
import com.zhengcheng.mall.bbs.domain.entity.Member;

/**
 * Service - 个人消息
 *
 * @author zqs
 * @version 3.0
 */
public interface MessageService extends BaseService<BbsMessage, Long> {

    /**
     * 分页查询
     *
     * @param member   会员
     * @param pageable 分页
     * @return 消息
     */
    Page<BbsMessage> findPage(Member member, Pageable pageable);

    /**
     * 查询会员的个人消息
     *
     * @param member 会员
     * @return
     */
    List<BbsMessage> findList(Member member);

    /**
     * 统计消息个数
     *
     * @param receiverRead 阅读
     * @param member       会员
     * @return
     */
    Long count(Boolean receiverRead, Member member);

    /**
     * 阅读所有消息
     *
     * @param member 会员
     */
    void readAll(Member member);
}
