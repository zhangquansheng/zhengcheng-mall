package com.zhengcheng.mall.admin.common.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.zhengcheng.mall.api.dto.TokenInfoDTO;



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
    private static final String DEFAULT_LOGIN_URL = "/login";

    /**
     * 登录URL
     */
    private String loginUrl = DEFAULT_LOGIN_URL;

    public static final String PRINCIPAL_ATTRIBUTE_NAME = "USER.PRINCIPAL";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        TokenInfoDTO principal = (TokenInfoDTO) session.getAttribute(PRINCIPAL_ATTRIBUTE_NAME);
        if (principal != null) {
            return true;
        } else {
            String requestType = request.getHeader("X-Requested-With");
            if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
                response.addHeader("loginStatus", "accessDenied");
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            } else {
                if (request.getMethod().equalsIgnoreCase("GET")) {
                    String redirectUrl = request.getQueryString() != null ? request.getRequestURI() + "?" + request.getQueryString() : request.getRequestURI();
                    response.sendRedirect(request.getContextPath() + loginUrl + "?" + REDIRECT_URL_PARAMETER_NAME + "=" + URLEncoder.encode(redirectUrl, "UTF-8"));
                } else {
                    response.sendRedirect(request.getContextPath() + loginUrl);
                }
                return false;
            }
        }
    }

}