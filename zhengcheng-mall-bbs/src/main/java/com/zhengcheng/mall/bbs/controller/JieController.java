package com.zhengcheng.mall.bbs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zhengcheng.mall.bbs.common.apiresult.ApiResult;
import com.zhengcheng.mall.bbs.common.constant.BBSContant;
import com.zhengcheng.mall.bbs.domain.entity.Ad;
import com.zhengcheng.mall.bbs.domain.entity.FriendLink;
import com.zhengcheng.mall.bbs.domain.entity.Jie;
import com.zhengcheng.mall.bbs.domain.entity.Member;
import com.zhengcheng.mall.bbs.domain.enums.AuditStatus;
import com.zhengcheng.mall.bbs.domain.enums.JieOrderAttr;
import com.zhengcheng.mall.bbs.domain.enums.JieType;
import com.zhengcheng.mall.bbs.service.*;

/**
 * 帖子
 *
 * @author zqs
 * @version 3.0
 */
@Controller("jieController")
@RequestMapping("/jie")
public class JieController extends BaseController {

    @Autowired
    private JieCategoryService jieCategoryService;
    @Autowired
    private MemberService      memberService;
    @Autowired
    private JieService         jieService;
    @Autowired
    private FriendLinkService  friendLinkService;
    @Autowired
    private AdService          adService;

    /**
     * 帖子列表
     *
     * @return
     */
    @RequestMapping("/{categoryId}/{jieType}/{jieOrderAttr}")
    public String list(@PathVariable("categoryId") Long categoryId, @PathVariable("jieType") JieType jieType,
                       @PathVariable("jieOrderAttr") JieOrderAttr jieOrderAttr, Integer pageNum, ModelMap model) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 0;
        } else {
            pageNum = pageNum - 1;
        }
        Pageable pageable = PageRequest.of(pageNum, BBSContant.PAGE_SIZE);
        if (jieOrderAttr.equals(JieOrderAttr.newest)) {
            pageable = PageRequest.of(pageNum, BBSContant.PAGE_SIZE, Sort.Direction.DESC, "createTime");
        } else if (jieOrderAttr.equals(JieOrderAttr.commentmost)) {
            pageable = PageRequest.of(pageNum, BBSContant.PAGE_SIZE, Sort.Direction.DESC, "commentNums");
        }
        Boolean good = null;
        Boolean finished = null;
        if (jieType.equals(JieType.solved)) {
            finished = true;
        } else if (jieType.equals(JieType.unsolved)) {
            finished = false;
        } else if (jieType.equals(JieType.wonderful)) {
            good = true;
        }
        Page<Jie> page = jieService.findPage(null, good, finished, AuditStatus.adopt,
                jieCategoryService.find(categoryId), null, null, pageable);

        Page<Jie> weekHotPage = jieService.findPage(null, 0L, AuditStatus.adopt,
                PageRequest.of(0, BBSContant.PAGE_SIZE, Sort.Direction.DESC, "commentNums"));

        //首页全部分类下，显示置顶的帖子
        if (categoryId.equals(BBSContant.ALL_JIE_CATEGORY_ID)) {
            model.addAttribute("topJieList",
                    jieService
                            .findPage(true, null, null, AuditStatus.adopt, null, null, null,
                                    PageRequest.of(0, BBSContant.PAGE_SIZE, Sort.Direction.DESC, "createTime"))
                            .getContent());
        }

        List<FriendLink> textFriendLinks = friendLinkService.findList(FriendLink.Type.text, FriendLink.Position.link);
        List<FriendLink> listStaticFriendLinks = friendLinkService.findList(FriendLink.Type.text,
                FriendLink.Position.listStatic);

        List<Ad> ads = adService.findAll();

        /**
         * 本周热议
         */
        model.addAttribute("weekHotPage", weekHotPage);
        /**
         * 钻级赞助商
         */
        model.addAttribute("ads", ads);

        model.addAttribute("newsList", Lists.newArrayList());

        model.addAttribute("textFriendLinks", textFriendLinks);
        model.addAttribute("listStaticFriendLinks", listStaticFriendLinks);

        model.addAttribute("page", page);
        model.addAttribute("pageNum", pageNum + 1);
        model.addAttribute("hasNext", page.hasNext());

        /**
         * 判断用户是否登录，返回用户登录后的信息
         */
        Boolean isAuthenticated = memberService.isAuthenticated();
        Boolean isSign = false;
        if (isAuthenticated) {
            Member member = memberService.getCurrent();
            isSign = memberService.isSign(member);
            model.addAttribute("member", member);
            //            model.addAttribute("kiss", BbsUtils.getKiss(member.getSignNonstopCount() + 1));
        }
        model.addAttribute("isSign", isSign);

        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("jieCategories", jieCategoryService.findAll());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("jieType", jieType);
        model.addAttribute("jieOrderAttr", jieOrderAttr);
        return "/jie/list";
    }

    /**
     * 帖子详情
     *
     * @param model
     * @return
     */
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap model) {
        Jie jie = jieService.find(id);
        /**
         * 这里应该使用消息队队列比较好
         */
        jie.setHits(jie.getHits() + 1);
        jieService.update(jie);

        Page<Jie> weekHotPage = jieService.findPage(null, 0L, AuditStatus.adopt,
                PageRequest.of(0, BBSContant.PAGE_SIZE, Sort.Direction.DESC, "commentNums"));
        Boolean isAuthenticated = memberService.isAuthenticated();
        if (isAuthenticated) {
            model.addAttribute("member", memberService.getCurrent());
        }
        List<Ad> ads = adService.findAll();

        /**
         * 登录以后跳转的页面
         */
        model.addAttribute("redirectUrl", "/jie/detail/" + id);
        /**
         * 本周热议
         */
        model.addAttribute("weekHotPage", weekHotPage);
        /**
         * 钻级赞助商
         */
        model.addAttribute("ads", ads);
        model.addAttribute("isAuthenticated", isAuthenticated);
        model.addAttribute("jie", jie);
        model.addAttribute("jieCategories", jieCategoryService.findAll());
        model.addAttribute("categoryId", BBSContant.NO_SELECT_JIE_CATEGORY_ID);
        return "jie/detail";
    }

    /**
     * 查询帖子内容详情
     *
     * @param id 帖子ID
     * @return
     */
    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> content(@PathVariable("id") Long id) {
        Jie jie = jieService.find(id);
        Map<String, Object> data = new HashMap<>();
        data.put("content", jie.getContent());
        return ApiResult.SUCCESS.getMap().add("data", data);
    }
}
