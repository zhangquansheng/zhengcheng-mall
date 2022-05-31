package com.zhengcheng.mall.bbs.domain.dao;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zhengcheng.mall.bbs.domain.entity.Jieda;

/**
 * Dao - 回帖
 *
 * @author zqs
 * @version 3.0
 */
public interface JiedaDao extends JpaRepository<Jieda, Long>, JpaSpecificationExecutor<Jieda> {

    /**
     * 根据ID，并加上锁查询
     *
     * @param id
     * @return
     */
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select jd from Jieda jd where jd.id = :id ")
    Jieda findJiedaByIdForUpdate(@Param("id") Long id);
}
