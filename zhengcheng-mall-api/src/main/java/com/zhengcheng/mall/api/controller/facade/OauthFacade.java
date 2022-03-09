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

}
