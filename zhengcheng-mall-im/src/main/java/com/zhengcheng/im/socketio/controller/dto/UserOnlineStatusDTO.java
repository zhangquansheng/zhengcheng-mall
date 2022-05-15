package com.zhengcheng.im.socketio.controller.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * UserLoginStatusDTO
 *
 * @author quansheng1.zhang
 * @since 2022/5/15 19:38
 */
@Data
public class UserOnlineStatusDTO implements Serializable {

    private static final long serialVersionUID = 6307503444962648243L;
    @ApiModelProperty("用户ID")
    private String            userId;
    @ApiModelProperty("是否在线")
    private Boolean           online;
}
