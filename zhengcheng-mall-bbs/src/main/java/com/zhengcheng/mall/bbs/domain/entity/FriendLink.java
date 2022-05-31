package com.zhengcheng.mall.bbs.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

/**
 * Entity - 友情链接
 *
 * @author zqs
 * @version 3.0
 */
@Entity
@Table(name = "friend_link")
public class FriendLink extends OrderEntity {

    private static final long serialVersionUID = 3019642557500517628L;

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
        image
    }

    public enum Position {

        /**
         * 温馨通道
         */
        listStatic,

        /**
         * 友情链接
         */
        link

    }

    /**
     * 名称
     */
    private String   name;

    /**
     * 类型
     */
    private Type     type;

    /**
     * 位置
     */
    private Position position;

    /**
     * logo
     */
    private String   logo;

    /**
     * 链接地址
     */
    private String   url;

    /**
     * 获取名称
     *
     * @return 名称
     */
    @Length(max = 200)
    @Column(nullable = false, length = 200)
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取类型
     *
     * @return 类型
     */
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
     * 获取位置
     *
     * @return 位置
     */
    @Column(nullable = false)
    public Position getPosition() {
        return position;
    }

    /**
     * 设置位置
     *
     * @param position 位置
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * 获取logo
     *
     * @return logo
     */
    @Length(max = 200)
    public String getLogo() {
        return logo;
    }

    /**
     * 设置logo
     *
     * @param logo logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 获取链接地址
     *
     * @return 链接地址
     */
    @Length(max = 200)
    @Column(nullable = false, length = 200)
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

}
