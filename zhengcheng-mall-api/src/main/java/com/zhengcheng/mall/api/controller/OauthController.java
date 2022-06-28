package com.zhengcheng.mall.api.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.controller.facade.OauthFacade;
import com.zhengcheng.mall.api.dto.TokenInfoDTO;
import com.zhengcheng.mall.api.feign.OauthFeignClient;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiOperation;

/**
 * 令牌申请接口
 *
 * @author quansheng1.zhang
 * @since 2021/7/15 14:20
 */
@RestController
@RequestMapping("/oauth")
public class OauthController implements OauthFeignClient {

    @Autowired
    private OauthFacade userFacade;

    @ApiOperation(value = "指定token的会话注销登录")
    @GetMapping(value = "/logoutByToken")
    @Override
    public Result<Void> logoutByToken(@RequestParam("token") String token) {
        StpUtil.logoutByTokenValue(token);
        return Result.success();
    }

    @SentinelResource("/tokenSentinelResource")
    @ApiOperation(value = "password获取token")
    @GetMapping("/token")
    @Override
    public Result<TokenInfoDTO> getToken(@RequestParam("username") String username,
                                         @RequestParam("enPassword") String enPassword) {
        SaTokenInfo saTokenInfo = userFacade.login(username, enPassword, getRequest());
        return Result.successData(BeanUtil.copyProperties(saTokenInfo, TokenInfoDTO.class));
    }

    @ApiOperation(value = "password获取token")
    @PostMapping("/token")
    @Override
    public Result<TokenInfoDTO> postToken(@RequestParam("username") String username,
                                          @RequestParam("password") String password) {
        SaTokenInfo saTokenInfo = userFacade.login(username, password, getRequest());
        return Result.successData(BeanUtil.copyProperties(saTokenInfo, TokenInfoDTO.class));
    }

    @Nullable
    private HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
        return Objects.nonNull(servletRequestAttributes) ? servletRequestAttributes.getRequest() : null;
    }

    @Nullable
    private ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }
}
