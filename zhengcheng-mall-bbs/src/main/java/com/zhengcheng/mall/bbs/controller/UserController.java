package com.zhengcheng.mall.bbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.mall.bbs.common.apiresult.ApiResult;
import com.zhengcheng.mall.bbs.domain.entity.Member;
import com.zhengcheng.mall.bbs.domain.enums.AuditStatus;
import com.zhengcheng.mall.bbs.service.JieService;
import com.zhengcheng.mall.bbs.service.JiedaService;
import com.zhengcheng.mall.bbs.service.MemberService;

import cn.hutool.core.date.DateUtil;

/**
 * 用户
 *
 * @author zqs
 * @version 3.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 每页记录数
     */
    private static final int PAGE_SIZE = 20;

    @Autowired
    private MemberService    memberService;
    @Autowired
    private JieService       jieService;
    @Autowired
    private JiedaService     jiedaService;

    /**
     * 根据昵称查询用户首页
     */
    @RequestMapping("/jump")
    public String jump(String nickname, ModelMap model) {
        Member member = memberService.findByNickname(nickname);
        if (member == null) {
            return "redirect:/";
        }
        Boolean isAuthenticated = memberService.isAuthenticated();
        if (isAuthenticated) {
            model.addAttribute("member", memberService.getCurrent());
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("user", member); //为了区分登录的会员和查询的会员
        model.addAttribute("jiePage", jieService.findPage(null, null, null, AuditStatus.adopt, null, member, null,
                PageRequest.of(0, PAGE_SIZE, Sort.Direction.DESC, "createTime")));
        model.addAttribute("jiedaPage",
                jiedaService.findPage(member, PageRequest.of(0, PAGE_SIZE, Sort.Direction.DESC, "createTime")));
        return "/user/home";
    }

    /**
     * 回贴周榜
     *
     * @return
     */
    @PostMapping("/top/reply")
    public @ResponseBody Map<String, Object> topReply() {
        Page<Member> weekReplyPage = memberService.findPage(0L, true,
                PageRequest.of(0, 20, Sort.Direction.DESC, "weekReplies"));
        return ApiResult.SUCCESS.getMap().add("data", toMapList(weekReplyPage.getContent()));
    }

    /**
     * 转换成map数组
     */
    private List<Map<String, Object>> toMapList(List<Member> members) {
        List<Map<String, Object>> newList = new ArrayList<>();
        if (members == null || members.size() == 0) {
            return newList;
        } else {
            for (Member member : members) {
                Map<String, Object> map = new HashMap<>();
                map.put("weekReplies", member.getWeekReplies());
                Map<String, Object> user = new HashMap<>();
                user.put("nickname", member.getNickname());
                user.put("avatar", member.getAvatar());
                map.put("user", user);
                newList.add(map);
            }
            return newList;
        }
    }

    /**
     * 签到活跃榜
     */
    @GetMapping("/sign/list")
    public @ResponseBody Map<String, Object> signList() {

        /**
         *最新签到
         */
        Page<Member> newPage = memberService
                .findPageBySignTime(PageRequest.of(0, PAGE_SIZE, Sort.Direction.DESC, "signLastTime"));
        List<List<Map<String, Object>>> data = new ArrayList();
        data.add(toSignMapList(newPage.getContent()));

        /**
         * 今日最快
         */
        Page<Member> fastPage = memberService
                .findPageBySignTime(PageRequest.of(0, PAGE_SIZE, Sort.Direction.ASC, "signLastTime"));
        data.add(toSignMapList(fastPage.getContent()));
        /**
         * 总签到榜
         */
        Page<Member> totalPage = memberService
                .findPageBySigncount(PageRequest.of(0, PAGE_SIZE, Sort.Direction.DESC, "signNonstopCount"));
        data.add(toSignMapList(totalPage.getContent()));
        return ApiResult.SUCCESS.getMap().add("data", data);
    }

    /**
     * 转换成map数组
     */
    private List<Map<String, Object>> toSignMapList(List<Member> members) {
        List<Map<String, Object>> newList = new ArrayList<>();
        if (members == null || members.size() == 0) {
            return newList;
        } else {
            for (Member member : members) {
                Map<String, Object> map = new HashMap<>();
                map.put("days", member.getSignNonstopCount());
                map.put("time", DateUtil.format(member.getSignLastTime(), "yyyy-MM-dd HH:mm:ss"));
                Map<String, Object> user = new HashMap<>();
                user.put("nickname", member.getNickname());
                user.put("avatar", member.getAvatar());
                map.put("user", user);
                newList.add(map);
            }
            return newList;
        }
    }

}
