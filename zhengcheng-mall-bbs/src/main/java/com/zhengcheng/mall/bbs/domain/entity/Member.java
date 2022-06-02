package com.zhengcheng.mall.bbs.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.zhengcheng.mall.bbs.domain.enums.Gender;

/**
 * Copyright © 2018 zskj Info. Tech Ltd. All rights reserved.
 * <p>
 * 功能描述：BBS会员
 *
 * @author 绽曙科技-zqs
 * @date: 2018/1/23
 */
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    private static final long serialVersionUID = 3621646795317079653L;
    /**
     * 昵称
     */
    private String            nickname;

    /**
     * 头像
     */
    private String            avatar;

    /**
     * 手机号
     */
    private String            phone;

    /**
     * 邮箱
     */
    private String            email;

    /**
     * 是否启用
     */
    private Boolean           enable;

    /**
     * 性別
     */
    private Gender            gender;

    /**
     * 城市
     */
    private String            city;

    /**
     * 签名
     */
    private String            slogn;

    /**
     * 管理员
     */
    private Boolean           admin;

    /**
     * 发帖权限
     */
    private Boolean           jieAuth;

    /**
     * 回复帖子权限
     */
    private Boolean           replyAuth;

    /**
     * 社区之光
     */
    private Boolean           light;

    /**
     * 飞吻
     */
    private Long              kiss;

    /**
     * 回帖总次数
     */
    private Long              replies;

    /**
     * 周回帖
     */
    private Long              weekReplies;

    /**
     * 最近回帖的周
     */
    private Date              lastReplyWeek;

    /**
     * 认证信息
     */
    private String            certified;

    /**
     * VIP信息
     */
    private String            vip;

    /**
     * 以下sign开头的都是签到信息
     */
    /**
     * 连续签到次数
     */
    private Long              signNonstopCount;

    /**
     * 签到总次数
     */
    private Long              signTotalCount;

    /**
     * 最后签到时间
     */
    private Date              signLastTime;

    /**
     * 最后签到获得的飞吻
     */
    private Long              signLastKiss;

    /**
     * 我的消息
     */
    private Set<BbsMessage>   bbsMessages      = new HashSet<>();

    /**
     * 我的帖子
     */
    private Set<Jie>          jies             = new HashSet<>();

    /**
     * 我的回帖
     */
    private Set<Jieda>        jiedas           = new HashSet<>();

    /**
     * 赞的回帖
     */
    private Set<Jieda>        zanJiedas        = new HashSet<>();

    /**
     * 艾特我的回帖
     */
    private Set<Jieda>        aitJiedas        = new HashSet<>();

    /**
     * 收藏帖子
     */
    private Set<Jie>          favoriteJies     = new HashSet<>();

    /**
     * 获取昵称
     *
     * @return 昵称
     */
    @Column(nullable = false, unique = true, length = 300)
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取头像
     *
     * @return 头像
     */
    @Column(length = 300)
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取常用手机号
     *
     * @return 常用手机号
     */
    @Column(unique = true, length = 50)
    public String getPhone() {
        return phone;
    }

    /**
     * 设置常用手机号
     *
     * @param phone 常用手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     *
     * @return 邮箱
     */
    @Column(unique = true, length = 50)
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取是否启用
     *
     * @return 是否启用
     */
    @Column(nullable = false)
    public Boolean getEnable() {
        return enable;
    }

    /**
     * 设置是否启用
     *
     * @param enable 是否启用
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * 获取城市
     *
     * @return 城市
     */
    @Column(length = 50)
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取管理员
     *
     * @return 管理员
     */
    @Column(nullable = false)
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * 设置管理员
     *
     * @param admin 管理员
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    /**
     * 获取发帖权限
     *
     * @return 发帖权限
     */
    @Column(nullable = false)
    public Boolean getJieAuth() {
        return jieAuth;
    }

    /**
     * 设置发帖权限
     *
     * @param jieAuth 发帖权限
     */
    public void setJieAuth(Boolean jieAuth) {
        this.jieAuth = jieAuth;
    }

    /**
     * 获取回帖权限
     *
     * @return 回帖权限
     */
    @Column(nullable = false)
    public Boolean getReplyAuth() {
        return replyAuth;
    }

    /**
     * 设置回帖权限
     *
     * @param replyAuth 回帖权限
     */
    public void setReplyAuth(Boolean replyAuth) {
        this.replyAuth = replyAuth;
    }

    /**
     * 获取社区之光
     *
     * @return 社区之光
     */
    @Column(nullable = false)
    public Boolean getLight() {
        return light;
    }

    /**
     * 设置社区之光
     *
     * @param light 社区之光
     */
    public void setLight(Boolean light) {
        this.light = light;
    }

    /**
     * 获取性别
     *
     * @return 性别
     */
    @Column(nullable = false)
    public Gender getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 获取飞吻
     *
     * @return 飞吻
     */
    @Column(nullable = false)
    public Long getKiss() {
        return kiss;
    }

    /**
     * 设置飞吻
     *
     * @param kiss 飞吻
     */
    public void setKiss(Long kiss) {
        this.kiss = kiss;
    }

    /**
     * 获取回帖数
     *
     * @return 回帖数
     */
    @Column(nullable = false)
    public Long getReplies() {
        return replies;
    }

    /**
     * 设置回帖数
     *
     * @param replies 回帖数
     */
    public void setReplies(Long replies) {
        this.replies = replies;
    }

    /**
     * 获取周回帖数
     *
     * @return 回帖数
     */
    @Column(nullable = false)
    public Long getWeekReplies() {
        return weekReplies;
    }

    /**
     * 设置周回帖数
     *
     * @param weekReplies 周回帖数
     */
    public void setWeekReplies(Long weekReplies) {
        this.weekReplies = weekReplies;
    }

    /**
     * 获取最近回帖的周
     *
     * @return 最近回帖的周
     */
    public Date getLastReplyWeek() {
        return lastReplyWeek;
    }

    /**
     * 最近回帖的周
     *
     * @param lastReplyWeek 最近回帖的周
     */
    public void setLastReplyWeek(Date lastReplyWeek) {
        this.lastReplyWeek = lastReplyWeek;
    }

    /**
     * 获取签名
     *
     * @return 签名
     */
    @Column(length = 300)
    public String getSlogn() {
        return slogn;
    }

    /**
     * 设置签名
     *
     * @param slogn 签名
     */
    public void setSlogn(String slogn) {
        this.slogn = slogn;
    }

    /**
     * 获取认证信息
     *
     * @return 认证信息
     */
    @Column(nullable = false, length = 50)
    public String getCertified() {
        return certified;
    }

    /**
     * 设置认证信息
     *
     * @param certified 认证信息
     */
    public void setCertified(String certified) {
        this.certified = certified;
    }

    /**
     * 获取VIP信息
     *
     * @return VIP信息
     */
    @Column(length = 50)
    public String getVip() {
        return vip;
    }

    /**
     * 设置VIP信息
     *
     * @param vip VIP信息
     */
    public void setVip(String vip) {
        this.vip = vip;
    }

    /**
     * 获取连续签到的次数
     *
     * @return 连续签到的次数
     */
    @Column(nullable = false)
    public Long getSignNonstopCount() {
        return signNonstopCount;
    }

    /**
     * 设置连续签到的次数
     *
     * @param signNonstopCount 连续签到的次数
     */
    public void setSignNonstopCount(Long signNonstopCount) {
        this.signNonstopCount = signNonstopCount;
    }

    /**
     * 获取签到总次数
     *
     * @return 签到总次数
     */
    @Column(nullable = false)
    public Long getSignTotalCount() {
        return signTotalCount;
    }

    /**
     * 设置签到总次数
     *
     * @param signTotalCount 签到总次数
     */
    public void setSignTotalCount(Long signTotalCount) {
        this.signTotalCount = signTotalCount;
    }

    /**
     * 获取最后签到的时间
     *
     * @return 最后签到的时间
     */
    public Date getSignLastTime() {
        return signLastTime;
    }

    /**
     * 设置最后签到的时间
     *
     * @param signLastTime 最后签到的时间
     */
    public void setSignLastTime(Date signLastTime) {
        this.signLastTime = signLastTime;
    }

    /**
     * 获取最后签到获取的飞吻
     *
     * @return 最后签到获取的飞吻
     */
    @Column(nullable = false)
    public Long getSignLastKiss() {
        return signLastKiss;
    }

    /**
     * 设置最后签到获取的飞吻
     *
     * @param signLastKiss 最后签到获取的飞吻
     */
    public void setSignLastKiss(Long signLastKiss) {
        this.signLastKiss = signLastKiss;
    }

    /**
     * 获取我的消息
     *
     * @return 我的消息
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<BbsMessage> getBbsMessages() {
        return bbsMessages;
    }

    /**
     * 设置我的消息
     *
     * @param bbsMessages 我的消息
     */
    public void setBbsMessages(Set<BbsMessage> bbsMessages) {
        this.bbsMessages = bbsMessages;
    }

    /**
     * 获取我的帖子
     *
     * @return 我的帖子
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Jie> getJies() {
        return jies;
    }

    /**
     * 设置我的帖子
     *
     * @param jies 我的帖子
     */
    public void setJies(Set<Jie> jies) {
        this.jies = jies;
    }

    /**
     * 获取我的回帖
     *
     * @return 我的回帖
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Jieda> getJiedas() {
        return jiedas;
    }

    /**
     * 设置我的回帖
     *
     * @param jiedas 我的回帖
     */
    public void setJiedas(Set<Jieda> jiedas) {
        this.jiedas = jiedas;
    }

    /**
     * 获取赞的回帖
     *
     * @return 赞的回帖
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "member_zan_jieda")
    @OrderBy("createTime desc")
    public Set<Jieda> getZanJiedas() {
        return zanJiedas;
    }

    /**
     * 设置赞的回帖
     *
     * @param zanJiedas 赞的回帖
     */
    public void setZanJiedas(Set<Jieda> zanJiedas) {
        this.zanJiedas = zanJiedas;
    }

    /**
     * 获取艾特我的回帖
     *
     * @return 艾特我的回帖
     */
    @ManyToMany(mappedBy = "aitMembers", fetch = FetchType.LAZY)
    public Set<Jieda> getAitJiedas() {
        return aitJiedas;
    }

    /**
     * 设置艾特我的回帖
     *
     * @param aitJiedas 艾特我的回帖
     */
    public void setAitJiedas(Set<Jieda> aitJiedas) {
        this.aitJiedas = aitJiedas;
    }

    /**
     * 获取我收藏的帖子
     *
     * @return 我收藏的帖子
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "member_favorite_jie")
    @OrderBy("createTime desc")
    public Set<Jie> getFavoriteJies() {
        return favoriteJies;
    }

    /**
     * 设置我收藏的帖子
     *
     * @param favoriteJies 我收藏的帖子
     */
    public void setFavoriteJies(Set<Jie> favoriteJies) {
        this.favoriteJies = favoriteJies;
    }
}
