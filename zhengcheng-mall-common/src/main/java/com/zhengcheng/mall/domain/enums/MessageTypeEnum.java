package com.zhengcheng.mall.domain.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.Getter;

/**
 * 消息类型
 *
 * @author :    zhangquansheng
 * @date :    2020/1/6 15:45
 */
@Getter
public enum MessageTypeEnum {

    NOTICE(0, "通知"),

    NEWS(1, "消息"),

    TODO(2, "待办");

    @EnumValue
    private final int    value;

    private final String desc;

    MessageTypeEnum(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

}
