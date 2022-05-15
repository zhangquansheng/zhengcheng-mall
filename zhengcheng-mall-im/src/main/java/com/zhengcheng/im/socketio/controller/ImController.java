package com.zhengcheng.im.socketio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.im.socketio.controller.dto.UserOnlineStatusDTO;
import com.zhengcheng.im.socketio.handler.AdminImHandler;
import com.zhengcheng.mall.domain.entity.Message;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ImController
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:50
 */
@Api(tags = { "即时消息接口" })
@RestController
@RequestMapping("/im")
public class ImController {

    @Autowired
    private AdminImHandler adminImHandler;

    @ApiOperation("管理后台即时通信发送消息")
    @PostMapping("/admin/sendMessage")
    public Result<Boolean> sendMessage(@RequestBody Message message) {
        return Result.successData(adminImHandler.sendMessage(message));
    }

    @ApiOperation("批量查询用户在线状态")
    @GetMapping("/user/online/status")
    public Result<List<UserOnlineStatusDTO>> userOnlineStatus(@RequestParam("userIds") List<String> userIds) {
        return Result.successData(adminImHandler.userOnlineStatus(userIds));
    }

}
