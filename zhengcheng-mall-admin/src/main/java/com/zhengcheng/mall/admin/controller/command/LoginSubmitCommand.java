package com.zhengcheng.mall.admin.controller.command;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录提交
 *
 * @author :    zhangquansheng
 * @date :    2020/1/15 17:25
 */
@Data
public class LoginSubmitCommand implements Serializable {

    private static final long serialVersionUID = -5763931944518625059L;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("RSA加密密码")
    private String enPassword;
}
