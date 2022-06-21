package com.zhengcheng.mall.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.UserService;
import com.zhengcheng.mall.dto.UserByNoQry;
import com.zhengcheng.mall.dto.data.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * UserController
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 10:22
 */
@Api(tags = { "用户(User)接口" })
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("通过用户编号获取用户消息")
    @GetMapping("/getByUserNo")
    public Result<UserDTO> getByUserNo(@RequestParam("userNo") String userNo) {
        UserByNoQry userByNoQry = new UserByNoQry();
        userByNoQry.setUserNo(userNo);
        return Result.successData(userService.getByUserNo(userByNoQry));
    }

}
