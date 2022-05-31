package com.zhengcheng.mall.bbs.domain.dao;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zhengcheng.mall.bbs.domain.entity.Member;

/**
 * The Interface MemberJpaDao.
 *
 * @author SHOP++ Team
 * @version 3.0
 */
public interface MemberDao extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    /**
     * 根据用户名查询
     */
    Member findByUsername(String username);

    /**
     * 根据昵称查询
     */
    Member findByNickname(String nickname);

    /**
     * 根据ID，并加上锁查询
     *
     * @param id
     * @return
     */
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select m from Member m where m.id = :id ")
    Member findMemberByIdForUpdate(@Param("id") Long id);
}
