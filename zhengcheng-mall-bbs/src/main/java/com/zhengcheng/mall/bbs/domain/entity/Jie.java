package com.zhengcheng.mall.bbs.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhengcheng.mall.bbs.domain.enums.AuditStatus;

/**
 * 帖子
 *
 * @author zqs
 * @version 3.0
 */
@Entity
@Table(name = "jie")
public class Jie extends BaseEntity {

    private static final long serialVersionUID = 4014944561634405083L;

    /**
     * 标题
     */
    private String            title;

    /**
     * 内容
     */
    private String            content;

    /**
     * 悬赏飞吻
     */
    private Long              experience;

    /**
     * 审核状态
     */
    private AuditStatus       auditStatus;

    /**
     * 置顶
     */
    private Boolean           top;

    /**
     * 精品
     */
    private Boolean           good;

    /**
     * 结贴
     */
    private Boolean           finished;

    /**
     * 评论数
     */
    private Long              commentNums;

    /**
     * 点击数
     */
    private Long              hits;

    /**
     * 专栏
     */
    private JieCategory       category;

    /**
     * 会员
     */
    private Member            member;

    /**
     * 回帖
     */
    private Set<Jieda>        jiedas           = new HashSet<>();

    /**
     * 收藏的会员
     */
    private Set<Member>       favoriteMembers  = new HashSet<>();

    /**
     * 获取标题
     *
     * @return 标题
     */
    @Column(nullable = false, length = 300)
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
     * 获取内容
     *
     * @return 内容
     */
    @JSONField(serialize = false)
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
     * 获取悬赏飞吻
     *
     * @return 悬赏飞吻
     */
    @Column(nullable = false)
    public Long getExperience() {
        return experience;
    }

    /**
     * 设置悬赏飞吻
     *
     * @param experience 悬赏飞吻
     */
    public void setExperience(Long experience) {
        this.experience = experience;
    }

    /**
     * 获取审核状态
     *
     * @return 审核状态
     */
    @Column(nullable = false)
    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态
     *
     * @param auditStatus 审核状态
     */
    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取置顶
     *
     * @return 置顶
     */
    @Column(nullable = false)
    public Boolean getTop() {
        return top;
    }

    /**
     * 设置置顶
     *
     * @param top 置顶
     */
    public void setTop(Boolean top) {
        this.top = top;
    }

    /**
     * 获取精品
     *
     * @return 精品
     */
    @Column(nullable = false)
    public Boolean getGood() {
        return good;
    }

    /**
     * 设置精品
     *
     * @param good 精品
     */
    public void setGood(Boolean good) {
        this.good = good;
    }

    /**
     * 获取结贴
     *
     * @return 结贴
     */
    @Column(nullable = false)
    public Boolean getFinished() {
        return finished;
    }

    /**
     * 设置结贴
     *
     * @param finished 结贴
     */
    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    /**
     * 获取评论数
     *
     * @return 评论数
     */
    @Column(nullable = false)
    public Long getCommentNums() {
        return commentNums;
    }

    /**
     * 设置评论数
     *
     * @param commentNums 评论数
     */
    public void setCommentNums(Long commentNums) {
        this.commentNums = commentNums;
    }

    /**
     * 获取点击数
     *
     * @return 点击数
     */
    @Column(nullable = false)
    public Long getHits() {
        return hits;
    }

    /**
     * 设置点击数
     *
     * @param hits 点击数
     */
    public void setHits(Long hits) {
        this.hits = hits;
    }

    /**
     * 获取专栏
     *
     * @return 专栏
     */
    @JSONField(serialize = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, nullable = false)
    public JieCategory getCategory() {
        return category;
    }

    /**
     * 设置专栏
     *
     * @param category 专栏
     */
    public void setCategory(JieCategory category) {
        this.category = category;
    }

    /**
     * 获取会员
     *
     * @return 会员
     */
    @JSONField(serialize = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, nullable = false)
    public Member getMember() {
        return member;
    }

    /**
     * 设置会员
     *
     * @param member 会员
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * 获取回帖
     *
     * @return 回帖
     */
    @JSONField(serialize = false)
    @OneToMany(mappedBy = "jie", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("createTime desc")
    public Set<Jieda> getJiedas() {
        return jiedas;
    }

    /**
     * 设置回帖
     *
     * @param jiedas 回帖
     */
    public void setJiedas(Set<Jieda> jiedas) {
        this.jiedas = jiedas;
    }

    /**
     * 获取收藏的会员
     *
     * @return 收藏的会员
     */
    @JSONField(serialize = false)
    @ManyToMany(mappedBy = "favoriteJies", fetch = FetchType.LAZY)
    public Set<Member> getFavoriteMembers() {
        return favoriteMembers;
    }

    /**
     * 设置收藏的会员
     *
     * @param favoriteMembers 收藏的会员
     */
    public void setFavoriteMembers(Set<Member> favoriteMembers) {
        this.favoriteMembers = favoriteMembers;
    }

    /**
     * 删除前处理
     */
    @PreRemove
    public void preRemove() {
        Set<Member> favoriteMembers = getFavoriteMembers();
        if (favoriteMembers != null) {
            for (Member favoriteMember : favoriteMembers) {
                favoriteMember.getFavoriteJies().remove(this);
            }
        }
    }

}
