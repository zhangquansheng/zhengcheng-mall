package com.zhengcheng.mall.bbs.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity - 回帖
 *
 * @author zqs
 * @version 3.0
 */
@Entity
@Table(name = "jieda")
public class Jieda extends BaseEntity {

    private static final long serialVersionUID = 4836422730024602817L;
    /**
     * 内容
     */
    private String            content;

    /**
     * 赞的次数
     */
    private Long              zan;

    /**
     * 采纳
     */
    private Boolean           caina;

    /**
     * 帖子
     */
    private Jie               jie;

    /**
     * 会员
     */
    private Member            member;

    /**
     * 点赞会员
     */
    private Set<Member>       zanMembers       = new HashSet<>();

    /**
     * 艾特的会员
     */
    private Set<Member>       aitMembers       = new HashSet<>();

    /**
     * 获取内容
     *
     * @return 内容
     */
    @Lob
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取赞的次数
     *
     * @return
     */
    @Column(nullable = false)
    public Long getZan() {
        return zan;
    }

    /**
     * 设置赞的次数
     *
     * @param zan 赞的次数
     */
    public void setZan(Long zan) {
        this.zan = zan;
    }

    /**
     * 获取采纳
     *
     * @return 采纳
     */
    @Column(nullable = false)
    public Boolean getCaina() {
        return caina;
    }

    /**
     * 设置采纳
     *
     * @param caina 采纳
     */
    public void setCaina(Boolean caina) {
        this.caina = caina;
    }

    /**
     * 获取帖子
     *
     * @return 帖子
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, nullable = false)
    public Jie getJie() {
        return jie;
    }

    /**
     * 设置帖子
     *
     * @param jie 帖子
     */
    public void setJie(Jie jie) {
        this.jie = jie;
    }

    /**
     * 获取回帖人
     *
     * @return 回帖人
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, nullable = false)
    public Member getMember() {
        return member;
    }

    /**
     * 设置回帖人
     *
     * @param member 回帖人
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * 获取点赞会员
     *
     * @return 点赞会员
     */
    @ManyToMany(mappedBy = "zanJiedas", fetch = FetchType.LAZY)
    public Set<Member> getZanMembers() {
        return zanMembers;
    }

    /**
     * 设置点赞会员
     *
     * @param zanMembers 点赞会员
     */
    public void setZanMembers(Set<Member> zanMembers) {
        this.zanMembers = zanMembers;
    }

    /**
     * 获取艾特的会员
     *
     * @return 艾特的会员
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "xx_jieda_ait_member")
    @OrderBy("createDate desc")
    public Set<Member> getAitMembers() {
        return aitMembers;
    }

    /**
     * 设置艾特的会员
     *
     * @param aitMembers 艾特的会员
     */
    public void setAitMembers(Set<Member> aitMembers) {
        this.aitMembers = aitMembers;
    }

    /**
     * 删除前处理
     */
    @PreRemove
    public void preRemove() {
        Set<Member> zanMembers = getZanMembers();
        if (zanMembers != null) {
            for (Member zanMember : zanMembers) {
                zanMember.getFavoriteJies().remove(this);
            }
        }
    }
}
