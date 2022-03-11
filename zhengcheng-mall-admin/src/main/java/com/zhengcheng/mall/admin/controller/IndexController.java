package com.zhengcheng.mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController - 首页
 *
 * @author quansheng1.zhang
 * @since 2021/5/19 15:37
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("currentUserId", 1);
        return "index";
    }

}
