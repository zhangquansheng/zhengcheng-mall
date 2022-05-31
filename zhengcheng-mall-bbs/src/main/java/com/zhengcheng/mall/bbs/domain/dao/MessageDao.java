package com.zhengcheng.mall.bbs.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zhengcheng.mall.bbs.domain.entity.BbsMessage;
import com.zhengcheng.mall.bbs.domain.entity.Member;

/**
 * Interface - 个人消息
 *
 * @author zqs
 * @version 3.0
 */
public interface MessageDao extends JpaRepository<BbsMessage, Long>, JpaSpecificationExecutor<BbsMessage> {

    /**
     * 设置为已读
     *
     * @param member
     */
    @Modifying
    @Query("update BbsMessage m set m.receiverRead = true where m.member = :member")
    void updateReaded(@Param("member") Member member);
}
