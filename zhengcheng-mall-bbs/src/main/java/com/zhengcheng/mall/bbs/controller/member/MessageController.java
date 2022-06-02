package com.zhengcheng.mall.bbs.controller.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.mall.bbs.common.apiresult.ApiResult;
import com.zhengcheng.mall.bbs.common.constant.BBSContant;
import com.zhengcheng.mall.bbs.controller.BaseController;
import com.zhengcheng.mall.bbs.domain.entity.BbsMessage;
import com.zhengcheng.mall.bbs.domain.entity.Member;
import com.zhengcheng.mall.bbs.service.MemberService;
import com.zhengcheng.mall.bbs.service.MessageService;

import cn.hutool.core.date.DateUtil;

/**
 * Controller - 我的消息
 *
 * @author zqs
 * @version 3.0
 */
@Controller
@RequestMapping("/member/message")
public class MessageController extends BaseController {

    @Autowired
    private MemberService  memberService;
    @Resource(name = "bbsMessageServiceImpl")
    private MessageService messageService;

    /**
     * 我的消息
     */
    @GetMapping("/list")
    public String list(ModelMap model) {
        Member member = memberService.getCurrent();
        Pageable pageable = PageRequest.of(0, BBSContant.PAGE_SIZE, Sort.Direction.DESC, "createDate");
        Page<BbsMessage> page = messageService.findPage(member, pageable);
        model.addAttribute("nav", "message");
        model.addAttribute("member", member);
        model.addAttribute("page", page);
        return "member/message/list";
    }

    /**
     * 我的消息-异步返回
     */
    @PostMapping(value = "/find")
    public @ResponseBody Map<String, Object> find() {
        Member member = memberService.getCurrent();
        List<BbsMessage> bbsMessageList = messageService.findList(member);
        List<Map<String, Object>> rows = new ArrayList<>();
        for (BbsMessage bbsMessage : bbsMessageList) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", bbsMessage.getId());
            row.put("content", bbsMessage.getContent());
            row.put("time", DateUtil.format(bbsMessage.getCreateTime(), "yyyy/MM/dd ahh:mm"));
            rows.add(row);
        }
        return ApiResult.SUCCESS.getMap().add("rows", rows);
    }

    /**
     * 未读消息个数
     */
    @PostMapping(value = "/nums")
    public @ResponseBody Map<String, Object> nums() {
        Member member = memberService.getCurrent();
        Long count = messageService.count(false, member);
        return ApiResult.SUCCESS.getMap().add("count", count);
    }

    /**
     * 阅读消息
     */
    @PostMapping(value = "/read")
    public @ResponseBody Map<String, Object> read() {
        Member member = memberService.getCurrent();
        messageService.readAll(member);
        return ApiResult.SUCCESS.getMap();
    }

    /**
     * 删除、清空消息
     */
    @PostMapping(value = "/remove")
    public @ResponseBody Map<String, Object> remove(Long id, Boolean all) {
        if (null != all && all) {
            Member member = memberService.getCurrent();
            for (BbsMessage message : member.getBbsMessages()) {
                messageService.delete(message);
            }
        } else {
            BbsMessage message = messageService.find(id);
            Member member = memberService.getCurrent();
            if (member.getBbsMessages().contains(message)) {
                messageService.delete(id);
            }
        }
        return ApiResult.SUCCESS.getMap();
    }

}
