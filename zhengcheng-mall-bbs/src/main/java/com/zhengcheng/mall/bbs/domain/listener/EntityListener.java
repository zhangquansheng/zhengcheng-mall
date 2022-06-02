package com.zhengcheng.mall.bbs.domain.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.zhengcheng.mall.bbs.domain.entity.BaseEntity;

/**
 * 对实体的监听 Listener - 创建日期、修改日期处理
 *
 * @author zqs
 * @version 3.0
 */
public class EntityListener {

    /**
     * 保存前处理
     *
     * @param entity 基类
     */
    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
    }

    /**
     * 更新前处理
     *
     * @param entity 基类
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setUpdateTime(new Date());
    }

}
