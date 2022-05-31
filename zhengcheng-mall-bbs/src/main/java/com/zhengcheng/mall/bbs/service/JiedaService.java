package com.zhengcheng.mall.bbs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zhengcheng.mall.bbs.domain.entity.Jieda;
import com.zhengcheng.mall.bbs.domain.entity.Member;

/**
 * Service - 回帖
 *
 * @author zqs
 * @version 3.0
 */
public interface JiedaService extends BaseService<Jieda, Long> {

    /**
     * 用户采纳回帖
     *
     * @param memberId 会员ID
     * @param id       回帖ID
     * @return
     */
    boolean accept(Long memberId, Long id);

    /**
     * 分页查询
     *
     * @param member   会员
     * @param pageable 分页
     * @return
     */
    Page<Jieda> findPage(Member member, Pageable pageable);
}
