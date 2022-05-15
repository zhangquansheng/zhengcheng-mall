/*
 * Copyright (c) 2021. 众安医疗科技 All Rights Reserved.
 * FileName: CoreError.java
 * @version: 1.0
 */

package com.zhengcheng.mall.common.enums;

import java.text.MessageFormat;

import lombok.Getter;

/**
 * admin 应用的错误码
 *
 * @author quansheng1.zhang
 * @since 2022/4/21 16:56
 */
@Getter
public enum AdminError {

    /**
     * 权限相关错误401*
     */
    NOT_PERMISSION_EXCEPTION(40101, "{0}"),
    /**
     * 验证码相关错误501*
     */
    KAPTCHA_EXCEPTION(50101, "{0}");

    private final Integer code;

    private final String  message;

    AdminError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getErrorMsg(Object... params) {
        String errorMsg;
        if (params != null && params.length != 0) {
            MessageFormat msgFmt = new MessageFormat(this.message);
            errorMsg = msgFmt.format(params);
        } else {
            errorMsg = this.message;
        }

        return errorMsg;
    }
}
