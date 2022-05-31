package com.zhengcheng.mall.bbs.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service - 基类
 *
 * @author zqs
 * @version 3.0
 */
public interface BaseService<T, ID extends Serializable> {

    /**
     * 查找实体对象
     *
     * @param id ID
     * @return 实体对象，若不存在则返回null
     */
    T find(ID id);

    /**
     * 保存实体对象
     *
     * @param entity 实体对象
     */
    void save(T entity);

    /**
     * 更新实体对象
     *
     * @param entity 实体对象
     * @return 实体对象
     */
    T update(T entity);

    /**
     * 删除实体对象
     *
     * @param id ID
     */
    void delete(ID id);

    /**
     * 删除实体对象
     *
     * @param entity 实体对象
     */
    void delete(T entity);

    /**
     * 查找所有实体对象集合
     *
     * @return 所有实体对象集合
     */
    List<T> findAll();

    /**
     * 查找实体对象分页
     *
     * @param pageable 分页信息
     * @return 实体对象分页
     */
    Page<T> findPage(Pageable pageable);
}
