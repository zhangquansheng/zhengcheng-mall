package com.zhengcheng.mall.bbs.domain.entity;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;

/**
 * Entity - 消息
 *
 * @author zqs
 * @version 3.0
 */
@Entity
@Table(name = "bbs_message")
public class BbsMessage extends BaseEntity {

    private static final long serialVersionUID = -5035343536762850722L;

    /**
     * 内容
     */
    private String            content;

    /**
     * ip
     */
    private String            ip;

    /**
     * 收件人已读
     */
    private Boolean           receiverRead;

    /**
     * 收件人
     */
    private Member            member;

    /**
     * 获取内容
     *
     * @return 内容
     */
    @Length(max = 1000)
    @Column(nullable = false, updatable = false, length = 1000)
    public String getContent() {
        return content;
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取ip
     *
     * @return ip
     */
    @Column(updatable = false, length = 50)
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip
     *
     * @param ip ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取收件人已读
     *
     * @return 收件人已读
     */
    @Column(nullable = false)
    public Boolean getReceiverRead() {
        return receiverRead;
    }

    /**
     * 设置收件人已读
     *
     * @param receiverRead 收件人已读
     */
    public void setReceiverRead(Boolean receiverRead) {
        this.receiverRead = receiverRead;
    }

    /**
     * 获取收件人
     *
     * @return 收件人
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, nullable = false)
    public Member getMember() {
        return member;
    }

    /**
     * 设置收件人
     *
     * @param member 收件人
     */
    public void setMember(Member member) {
        this.member = member;
    }
}
