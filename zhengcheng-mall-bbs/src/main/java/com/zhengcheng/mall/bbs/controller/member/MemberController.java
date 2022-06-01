package com.zhengcheng.mall.bbs.controller.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.mall.bbs.common.apiresult.ApiResult;
import com.zhengcheng.mall.bbs.common.constant.BBSContant;
import com.zhengcheng.mall.bbs.controller.BaseController;
import com.zhengcheng.mall.bbs.domain.entity.Jie;
import com.zhengcheng.mall.bbs.domain.entity.Member;
import com.zhengcheng.mall.bbs.domain.enums.Gender;
import com.zhengcheng.mall.bbs.service.JieService;
import com.zhengcheng.mall.bbs.service.MemberService;

/**
 * 会员中心
 *
 * @author zqs
 * @version 3.0
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private JieService    jieService;

    /**
     * 账号设置
     */
    @GetMapping("/set")
    public String set(ModelMap model, HttpServletRequest request) {
        Member member = memberService.getCurrent();
        //        RSAPublicKey publicKey = rsaService.generateKey(request);
        //        String modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
        //        String exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
        model.addAttribute("nav", "set");
        model.addAttribute("modulus", "modulus");
        model.addAttribute("exponent", "modulus");
        model.addAttribute("member", member);
        return "member/set";
    }

    /**
     * 用户中心
     */
    @GetMapping("/index")
    public String index(ModelMap model) {
        Member member = memberService.getCurrent();
        Page<Jie> page = jieService.findPage(null, null, null, null, null, member, null,
                PageRequest.of(0, BBSContant.PAGE_SIZE, Sort.Direction.DESC, "createDate"));
        Page<Jie> favoritePage = jieService.findPage(null, null, null, null, null, null, member,
                PageRequest.of(0, BBSContant.PAGE_SIZE, Sort.Direction.DESC, "createDate"));
        model.addAttribute("nav", "index");
        model.addAttribute("member", member);
        model.addAttribute("pageSize", BBSContant.PAGE_SIZE);
        model.addAttribute("page", page);
        model.addAttribute("favoritePage", favoritePage);
        return "member/index";
    }

    /**
     * 社区管理
     */
    @GetMapping("/admin")
    public String admin(ModelMap model) {
        Member member = memberService.getCurrent();
        model.addAttribute("nav", "admin");
        model.addAttribute("member", member);
        return "member/admin";
    }

    /**
     * 更新我的资料
     */
    @RequestMapping(value = "/update_profile", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateProfile(String nickname, Gender gender, String city, String sign) {
        Member member = memberService.getCurrent();
        member.setNickname(nickname);
        member.setGender(gender);
        member.setCity(city);
        if (StringUtils.isEmpty(sign)) {
            member.setSlogn(null);
        } else {
            member.setSlogn(sign);
        }
        memberService.update(member);
        return ApiResult.SUCCESS.getMap().add("content", "更新我的资料成功！");
    }

    /**
     * 更新头像
     */
    @RequestMapping(value = "/update_avatar", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateProfile(String avatar) {
        if (StringUtils.isEmpty(avatar)) {
            return ApiResult.BUSINESS_FAIL.getMap().add("errorMsg", "请上传头像！");
        }
        memberService.updateAvatar(memberService.getCurrentID(), avatar);
        return ApiResult.SUCCESS.getMap();
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updatePassword(HttpServletRequest request) {
        //        String nowpass = rsaService.decryptParameter("enNowpass", request);
        //        String pass = rsaService.decryptParameter("enPass", request);
        //        rsaService.removePrivateKey(request);
        //        Member member = memberService.getCurrent();
        //        if (!StringUtils.equals(DigestUtils.md5Hex(nowpass), member.getPassword())) {
        //            return ApiResult.BUSINESS_FAIL.getMap().add("errorMsg", "原密码错误！");
        //        }
        //        member.setPassword(DigestUtils.md5Hex(pass));
        //        memberService.update(member);
        return ApiResult.SUCCESS.getMap();
    }

    /**
     * 签到
     */
    @PostMapping(value = "/sign")
    public @ResponseBody Map<String, Object> sign() {
        Member member = memberService.getCurrent();
        if (memberService.isSign(member)) {
            return ApiResult.BUSINESS_FAIL.getMap().add("errorMsg", "您今日已经签到，请勿重复！");
        }
        return ApiResult.SUCCESS.getMap().add("data", memberService.sign(member));
    }

    /**
     * 会员列表
     *
     * @param page  页面
     * @param limit 每页个数
     * @return
     */
    @GetMapping(value = "/list")
    public @ResponseBody Map<String, Object> list(Integer page, Integer limit) {
        Page<Member> memberPage = memberService
                .findPage(PageRequest.of(page - 1, limit, Sort.Direction.DESC, "createDate"));
        return ApiResult.SUCCESS.getMap().add("count", memberPage.getTotalElements()).add("data",
                memberPage.getContent());
    }

    /**
     * 更新会员的属性
     *
     * @param id    会员ID
     * @param field 会员属性名
     * @param value 会员属性值
     * @return
     */
    @RequestMapping(value = "/update_attr", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateAttr(Long id, String field, String value) {
        Member member = memberService.getCurrent();
        if (memberService.update(member, id, field, value)) {
            return ApiResult.SUCCESS.getMap();
        }
        return ApiResult.BUSINESS_FAIL.getMap().add("errorMsg", "您没有权限！");
    }

}
