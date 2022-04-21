package com.zhengcheng.mall.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhengcheng.mall.admin.common.interceptor.LoginInterceptor;
import com.zhengcheng.mall.api.dto.TokenInfoDTO;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * IndexController - 首页
 *
 * @author quansheng1.zhang
 * @since 2021/5/19 15:37
 */
@Slf4j
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        TokenInfoDTO tokenInfoDTO = (TokenInfoDTO) session.getAttribute(LoginInterceptor.PRINCIPAL_ATTRIBUTE_NAME);
        log.info(JSONUtil.toJsonStr(tokenInfoDTO));
        return "index";
    }

}
