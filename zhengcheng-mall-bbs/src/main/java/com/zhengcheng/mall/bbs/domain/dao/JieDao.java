package com.zhengcheng.mall.bbs.domain.dao;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zhengcheng.mall.bbs.domain.entity.Jie;

/**
 * Dao - 帖子
 *
 * @author zqs
 * @version 3.0
 */
public interface JieDao extends JpaRepository<Jie, Long>, JpaSpecificationExecutor<Jie> {

    /**
     * 根据ID，并加上锁查询
     *
     * @param id
     * @return
     */
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select j from Jie j where j.id = :id ")
    Jie findJieByIdForUpdate(@Param("id") Long id);
}
