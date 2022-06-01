package com.zhengcheng.mall.bbs.controller.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.mall.bbs.common.apiresult.ApiResult;
import com.zhengcheng.mall.bbs.controller.BaseController;
import com.zhengcheng.mall.bbs.domain.entity.Jie;
import com.zhengcheng.mall.bbs.domain.entity.Member;
import com.zhengcheng.mall.bbs.service.JieService;
import com.zhengcheng.mall.bbs.service.MemberService;

/**
 * Controller - 收藏
 *
 * @author zqs
 * @version 3.0
 */
@Controller
@RequestMapping("/member/favorite")
public class FavoriteController extends BaseController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private JieService    jieService;

    /**
     * 获取会员收藏帖子状态
     *
     * @param cid 帖子ID
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> find(Long cid) {
        Member member = memberService.getCurrent();
        Jie jie = jieService.find(cid);
        Boolean collection = false;
        if (member.getFavoriteJies().contains(jie)) {
            collection = true;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("collection", collection);
        return ApiResult.SUCCESS.getMap().add("data", data);
    }

    /**
     * 添加/取消收藏帖子
     *
     * @param type 类型【添加、取消】
     * @param cid  帖子ID
     * @return
     */
    @RequestMapping(value = "/{type}", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> addRemove(@PathVariable("type") String type, Long cid) {
        Member member = memberService.getCurrent();
        Jie jie = jieService.find(cid);
        if ("add".equalsIgnoreCase(type)) {
            member.getFavoriteJies().add(jie);
        } else if ("remove".equalsIgnoreCase(type)) {
            member.getFavoriteJies().remove(jie);
        }
        memberService.update(member);
        return ApiResult.SUCCESS.getMap();
    }

}
