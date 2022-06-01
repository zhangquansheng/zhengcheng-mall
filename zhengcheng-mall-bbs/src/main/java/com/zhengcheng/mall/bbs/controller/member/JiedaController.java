package com.zhengcheng.mall.bbs.controller.member;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.mall.bbs.common.apiresult.ApiResult;
import com.zhengcheng.mall.bbs.controller.BaseController;
import com.zhengcheng.mall.bbs.domain.entity.Jie;
import com.zhengcheng.mall.bbs.domain.entity.Jieda;
import com.zhengcheng.mall.bbs.domain.entity.Member;
import com.zhengcheng.mall.bbs.service.JieService;
import com.zhengcheng.mall.bbs.service.JiedaService;
import com.zhengcheng.mall.bbs.service.MemberService;

/**
 * controller - 回帖
 *
 * @author zqs
 * @version 3.0
 */
@Controller("memberJiedaController")
@RequestMapping("/member/jieda")
public class JiedaController extends BaseController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private JiedaService  jiedaService;
    @Autowired
    private JieService    jieService;

    /**
     * 赞
     */
    @PostMapping(value = "/zan")
    public @ResponseBody Map<String, Object> zan(Long id, Boolean ok) {
        Jieda jieda = jiedaService.find(id);
        Member member = memberService.getCurrent();
        if (member.equals(jieda.getMember())) {
            return ApiResult.BUSINESS_FAIL.getMap().add("errorMsg", "不能点赞自己！");
        }
        if ((null != ok) && ok) {
            jieda.setZan(jieda.getZan() - 1);
            if (!member.getZanJiedas().contains(jieda)) {
                return ApiResult.BUSINESS_FAIL.getMap().add("errorMsg", "你没有点赞，要怎么取消点赞！");
            }
            member.getZanJiedas().remove(jieda);
        } else {
            if (member.getZanJiedas().contains(jieda)) {
                return ApiResult.BUSINESS_FAIL.getMap().add("errorMsg", "你已经点赞，不要重复点赞！");
            }
            member.getZanJiedas().add(jieda);
            jieda.setZan(jieda.getZan() + 1);
        }
        memberService.update(member);
        jiedaService.update(jieda);
        return ApiResult.SUCCESS.getMap();
    }

    /**
     * 采纳
     *
     * @param id 回帖ID
     * @return
     */
    @PostMapping(value = "/accept")
    public @ResponseBody Map<String, Object> accept(Long id) {
        Long memberId = memberService.getCurrentID();
        if (jiedaService.accept(memberId, id)) {
            return ApiResult.SUCCESS.getMap();
        } else {
            return ApiResult.SUCCESS.getMap().add("errorMsg", "您没有权限！");
        }
    }

    /**
     * 获取解答
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/getDa")
    public @ResponseBody Map<String, Object> getDa(Long id) {
        Member member = memberService.getCurrent();
        if (member.getAdmin()) {
            Jieda jieda = jiedaService.find(id);
            return ApiResult.SUCCESS.getMap().add("data", jieda);
        }
        return ApiResult.BUSINESS_FAIL.getMap().add("errorMsg", "您没有权限！");
    }

    /**
     * 更新
     *
     * @param id      回帖ID
     * @param content 回帖内容
     * @return
     */
    @PostMapping(value = "/updateDa")
    public @ResponseBody Map<String, Object> updateDa(Long id, String content) {
        Member member = memberService.getCurrent();
        if (member.getAdmin()) {
            Jieda jieda = jiedaService.find(id);
            jieda.setContent(content);
            jiedaService.update(jieda);
            return ApiResult.SUCCESS.getMap();
        }
        return ApiResult.BUSINESS_FAIL.getMap().add("errorMsg", "您没有权限！");
    }

    /**
     * 删除
     *
     * @param id 回帖ID
     * @return
     */
    @PostMapping(value = "/delete")
    public @ResponseBody Map<String, Object> delete(Long id) {
        Member member = memberService.getCurrent();
        if (member.getAdmin()) {
            Jieda jieda = jiedaService.find(id);
            Jie jie = jieda.getJie();
            jie.setCommentNums(jie.getCommentNums() - 1);
            if (jieda.getCaina()) {
                jie.setFinished(false);
            }
            jieService.update(jie);

            jiedaService.delete(jieda);

            return ApiResult.SUCCESS.getMap();
        }
        return ApiResult.BUSINESS_FAIL.getMap().add("errorMsg", "您没有权限！");
    }

}
