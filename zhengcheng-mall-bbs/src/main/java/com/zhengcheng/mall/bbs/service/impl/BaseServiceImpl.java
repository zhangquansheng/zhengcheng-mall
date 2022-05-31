package com.zhengcheng.mall.bbs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.zhengcheng.mall.bbs.domain.entity.BaseEntity;
import com.zhengcheng.mall.bbs.service.BaseService;

/**
 * Service - 基类
 *
 * @author zqs
 * @version 3.0
 */
@Transactional
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    /**
     * 更新忽略属性
     */
    private static final String[] UPDATE_IGNORE_PROPERTIES = new String[] { BaseEntity.ID_PROPERTY_NAME,
            BaseEntity.CREATE_DATE_PROPERTY_NAME, BaseEntity.MODIFY_DATE_PROPERTY_NAME };

    /**
     * baseDao
     */
    private JpaRepository<T, ID>  baseDao;

    public void setBaseDao(JpaRepository<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    @Transactional(readOnly = true)
    public T find(ID id) {
        return baseDao.findOne(id);
    }

    @Transactional
    public void save(T entity) {
        baseDao.save(entity);
    }

    @Transactional
    public T update(T entity) {
        return baseDao.save(entity);
    }

    @Transactional
    public void delete(ID id) {
        baseDao.delete(id);
    }

    @Transactional
    public void delete(ID... ids) {
        if (ids != null) {
            for (ID id : ids) {
                baseDao.delete(id);
            }
        }
    }

    @Transactional
    public void delete(T entity) {
        baseDao.delete(entity);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Transactional(readOnly = true)
    public Page<T> findPage(Pageable pageable) {
        return baseDao.findAll(pageable);
    }
}
