package com.zhengcheng.mall.bbs.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity - 广告
 *
 * @author zqs
 * @version 3.0
 */
@Entity
@Table(name = "ad")
public class Ad extends OrderEntity {

    private static final long serialVersionUID = 4156340919481339638L;

    /**
     * 类型
     */
    public enum Type {

        /**
         * 文本
         */
        text,

        /**
         * 图片
         */
        image,

        /**
         * flash
         */
        flash
    }

    /**
     * 标题
     */
    private String title;

    /**
     * 类型
     */
    private Type   type;

    /**
     * 起始日期
     */
    private Date   beginDate;

    /**
     * 结束日期
     */
    private Date   endDate;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 获取标题
     *
     * @return 标题
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取类型
     *
     * @return 类型
     */
    @NotNull
    @Column(nullable = false)
    public Type getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * 获取起始日期
     *
     * @return 起始日期
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置起始日期
     *
     * @param beginDate 起始日期
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取结束日期
     *
     * @return 结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束日期
     *
     * @param endDate 结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取链接地址
     *
     * @return 链接地址
     */
    @Length(max = 200)
    public String getUrl() {
        return url;
    }

    /**
     * 设置链接地址
     *
     * @param url 链接地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 判断是否已开始
     *
     * @return 是否已开始
     */
    @Transient
    public boolean hasBegun() {
        return getBeginDate() == null || new Date().after(getBeginDate());
    }

    /**
     * 判断是否已结束
     *
     * @return 是否已结束
     */
    @Transient
    public boolean hasEnded() {
        return getEndDate() != null && new Date().after(getEndDate());
    }
}
