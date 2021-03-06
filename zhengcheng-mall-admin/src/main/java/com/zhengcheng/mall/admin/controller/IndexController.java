package com.zhengcheng.mall.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhengcheng.common.dto.UserDTO;
import com.zhengcheng.mall.common.interceptor.LoginInterceptor;

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

    @RequestMapping("/space")
    public String space() {
        return "/view/system/space";
    }

    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        UserDTO userInfo = (UserDTO) session.getAttribute(LoginInterceptor.PRINCIPAL_ATTRIBUTE_NAME);
        log.info(JSONUtil.toJsonStr(userInfo));
        return "index";
    }

    @RequestMapping("/view/console/console1")
    public String console1(Model model, HttpSession session) {
        return "/view/console/console1";
    }

    @RequestMapping("/view/console/console2")
    public String console2(Model model, HttpSession session) {
        return "/view/console/console2";
    }
}
