package com.zhengcheng.mall.admin.common.advice;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.core.advice.ExceptionControllerAdvice;
import com.zhengcheng.mall.admin.common.enums.AdminError;

import cn.dev33.satoken.exception.NotPermissionException;
import lombok.extern.slf4j.Slf4j;

/**
 * AdminExceptionControllerAdvice
 *
 * @author quansheng1.zhang
 * @since 2022/4/21 23:59
 */
@Slf4j
@Configuration
@RestControllerAdvice
public class AdminExceptionControllerAdvice extends ExceptionControllerAdvice {

    @ExceptionHandler({NotPermissionException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handleMaxUploadSizeExceededException(NotPermissionException e) {
        log.warn("NotPermissionException code :{} , message:{} ",e.getCode(), e.getMessage());
        return Result.create(AdminError.NotPermissionException.getCode(), e.getMessage());
    }

}
