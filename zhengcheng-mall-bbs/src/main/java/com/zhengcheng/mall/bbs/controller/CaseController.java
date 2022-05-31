package com.zhengcheng.mall.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhengcheng.mall.bbs.service.MemberService;

/**
 * Controller - 案例
 *
 * @author zqs
 * @version 3.0
 */
@Controller
@RequestMapping("/case")
public class CaseController extends BaseController {

    @Autowired
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping("list")
    public String list(ModelMap model) {
        return "case/list";
    }
}
