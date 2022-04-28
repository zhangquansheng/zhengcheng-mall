package com.zhengcheng.mall.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 消息状态
 *
 * @author :    zhangquansheng
 * @date :    2020/1/6 15:49
 */
@Getter
public enum MessageStatusEnum {

    RECEIVED(0, "已接收"),

    UNRECEIVED(1, "未接收");

    @EnumValue
    private final int    value;

    private final String desc;

    MessageStatusEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

}
