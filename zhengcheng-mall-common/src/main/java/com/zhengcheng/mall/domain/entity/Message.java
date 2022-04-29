package com.zhengcheng.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhengcheng.mall.domain.enums.MessageStatusEnum;
import com.zhengcheng.mall.domain.enums.MessageTypeEnum;
import com.zhengcheng.mybatis.plus.model.BaseEntity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Message
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 22:08
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("message")
public class Message extends BaseEntity<Message> {
    private static final long serialVersionUID = 6863148770169257961L;

    /**
     * 发送者ID
     */
    private Long              senderId;

    /**
     * 接收者ID
     */
    private Long              receiverId;

    /**
     * 消息类型
     */
    private MessageTypeEnum   type;

    /**
     * 消息状态
     */
    private MessageStatusEnum status;
    /**
     * 消息图标
     */
    private String            avatar;
    /**
     * 消息标题
     */
    private String            title;
    /**
     * 消息内容
     */
    private String            content;
}
