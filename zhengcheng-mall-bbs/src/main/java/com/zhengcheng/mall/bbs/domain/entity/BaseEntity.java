package com.zhengcheng.mall.bbs.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.groups.Default;

import com.zhengcheng.mall.bbs.domain.listener.EntityListener;

/**
 * 实体基类
 *
 * @author zqs
 * @version 3.0
 */
@EntityListeners(EntityListener.class)
@MappedSuperclass
public class BaseEntity implements Serializable {

    /**
     *
     */
    private static final long  serialVersionUID          = -6539507488478657268L;

    /**
     * "ID"属性名称
     */
    public static final String ID_PROPERTY_NAME          = "id";

    /**
     * "创建日期"属性名称
     */
    public static final String CREATE_DATE_PROPERTY_NAME = "createTime";

    /**
     * "修改日期"属性名称
     */
    public static final String MODIFY_DATE_PROPERTY_NAME = "updateTime";

    /**
     * 保存验证组
     */
    public interface Save extends Default {

    }

    /**
     * 更新验证组
     */
    public interface Update extends Default {

    }

    /**
     * ID
     */
    private Long id;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, updatable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 新版的修改时间可以为NULL
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 重写equals方法
     *
     * @param obj 对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!BaseEntity.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        return getId() != null ? getId().equals(other.getId()) : false;
    }

    /**
     * 重写hashCode方法
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }

}
