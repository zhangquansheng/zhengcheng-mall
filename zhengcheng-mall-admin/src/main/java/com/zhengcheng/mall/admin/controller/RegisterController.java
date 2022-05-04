package com.zhengcheng.mall.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.common.dto.UserDTO;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.UserCommand;
import com.zhengcheng.mall.api.feign.UserFeignClient;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户注册
 *
 * @author :    quansheng.zhang
 * @date :    2020/1/8 20:01
 */
@Api(tags = { "用户注册" })
@Controller
@RequestMapping("/reg")
public class RegisterController {

    @Autowired
    private UserFeignClient userFeignClient;

    @ApiOperation("注册页面")
    @GetMapping
    public String reg(ModelMap model) {
        model.addAttribute("publicKeyStr",
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2Nq+V4OqVHlIMxj7EVRW3Ofwm7E8sNf8rqmFoTpwvFnFwveKhsowZBjmH4Om9a7aQ6QqaOOMHe2URfhy5HuxhUIyq6Z6y3qF7i31wtbdCIEbmOobuW5oiHNF2AUQXQ752XrasEiuGom4JG1hgVIFAF68YIxeYzNgN8/I8AfxhsQIDAQAB");
        return "reg";
    }

    @ApiOperation("检查用户名是否被禁用或已存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string", paramType = "query") })
    @GetMapping(value = "/check-username")
    public @ResponseBody Result<Boolean> checkUsername(String username) {
        if (StrUtil.isEmpty(username)) {
            return Result.successData(false);
        }

        return Result.successData(!usernameExists(username));
    }

    @ApiOperation("注册提交")
    @PostMapping(value = "/submit")
    public @ResponseBody Result<Long> submit(String username, String enPassword, String nickname) {
        if (usernameExists(username)) {
            return Result.errorMessage("用户名已存在");
        }

        UserCommand userCommand = new UserCommand();
        userCommand.setUsername(username);
        userCommand.setPassword(enPassword);
        userCommand.setName(nickname);
        return userFeignClient.add(userCommand);
    }

    private boolean usernameExists(String username) {
        Result<UserDTO> userResult = userFeignClient.findByUsername(username);
        return userResult.hasData();
    }
}
