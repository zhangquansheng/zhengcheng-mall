package com.zhengcheng.mall.api.controller.facade;

import javax.servlet.http.HttpServletRequest;

import cn.dev33.satoken.stp.SaTokenInfo;

/**
 * IUserFacade
 *
 * @author quansheng1.zhang
 * @since 2021/7/15 14:27
 */
public interface OauthFacade {

    SaTokenInfo login(String username, String enPassword, HttpServletRequest request);

    /**
     * 微信小程序登录
     * 
     * @param appid 应用ID
     * @param code 登录时获取的 code
     * @param request HttpServletRequest
     * @return 用户相关信息
     */
    SaTokenInfo wxMaLogin(String appid, String code, HttpServletRequest request);
}
