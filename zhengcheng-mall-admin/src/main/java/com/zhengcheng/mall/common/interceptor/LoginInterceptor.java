package com.zhengcheng.mall.common.interceptor;

import java.net.URLEncoder;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import com.zhengcheng.mall.api.dto.TokenInfoDTO;
import com.zhengcheng.mall.common.constants.CommonConstant;
import com.zhengcheng.mall.common.holder.TokenInfoHolder;

import cn.hutool.core.util.StrUtil;

/**
 * Interceptor - 用户登录拦截器
 *
 * @author :    zhngquansheng
 * @date :    2019/12/20 15:17
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * "重定向URL"参数名称
     */
    private static final String REDIRECT_URL_PARAMETER_NAME = "redirectUrl";

    /**
     * 默认登录URL
     */
    private final String        DEFAULT_LOGIN_URL           = "/login";

    public static final String  PRINCIPAL_ATTRIBUTE_NAME    = "USER.PRINCIPAL";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //        HttpSession session = request.getSession();
        TokenInfoDTO principal = getTokenInfoDTO(request);
        //        String satoken = request.getHeader(CommonConstant.TOKEN_NAME);
        if (principal != null) {
            TokenInfoHolder.setTokenInfo(principal);
            return true;
        } else {
            String requestType = request.getHeader("X-Requested-With");
            if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
                response.addHeader("loginStatus", "accessDenied");
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            } else {
                if (request.getMethod().equalsIgnoreCase("GET")) {
                    String redirectUrl = request.getQueryString() != null
                            ? request.getRequestURI() + "?" + request.getQueryString()
                            : request.getRequestURI();
                    response.sendRedirect(request.getContextPath() + DEFAULT_LOGIN_URL + "?"
                            + REDIRECT_URL_PARAMETER_NAME + "=" + URLEncoder.encode(redirectUrl, "UTF-8"));
                } else {
                    response.sendRedirect(request.getContextPath() + DEFAULT_LOGIN_URL);
                }
                return false;
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {
        TokenInfoHolder.removeTokenInfo();
    }

    @Nullable
    private TokenInfoDTO getTokenInfoDTO(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TokenInfoDTO principal = (TokenInfoDTO) session.getAttribute(PRINCIPAL_ATTRIBUTE_NAME);
        if (Objects.nonNull(principal)) {
            return principal;
        }

        String satoken = request.getHeader(CommonConstant.TOKEN_NAME);
        if (StrUtil.isNotBlank(satoken)) {

        }
        return null;
    }
}
