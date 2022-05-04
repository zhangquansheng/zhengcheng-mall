package com.zhengcheng.mall.weixin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserTagService;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;

/**
 * 微信用户标签
 *
 * @author quansheng1.zhang
 * @since 2022/4/19 18:18
 */
@Slf4j
@Controller
@RequestMapping("/admin/wxusertag")
public class WxUserTagController {

    @Autowired
    private WxMpService wxService;

    @ApiOperation("微信用户标签页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/wxusertag";
    }

    @ApiOperation("新增微信用户标签页面")
    @GetMapping("/add")
    public String add() {
        return "/view/wxusertag/add";
    }

    @ApiOperation("微信用户标签列表")
    @SaCheckPermission("wxmp:wxusertag:list")
    @PostMapping("/list")
    public @ResponseBody Result<PageInfo<WxUserTag>> getWxusertags(String appId) {
        WxMpUserTagService wxMpUserTagService = wxService.getUserTagService();
        try {
            List<WxUserTag> listWxUserTags = wxMpUserTagService.tagGet();
            PageInfo<WxUserTag> pageInfo = new PageInfo<>();
            pageInfo.setRecords(listWxUserTags);
            return Result.successData(pageInfo);
        } catch (WxErrorException e) {
            log.error("获取微信用户标签失败", e);
            return Result.errorMessage(e.getMessage());
        }
    }

    @ApiOperation("新增微信用户标签")
    @PostMapping("/save")
    public @ResponseBody Result<WxUserTag> save(@RequestBody JSONObject data) {
        String appId = data.getStr("appId");
        String name = data.getStr("name");
        WxMpUserTagService wxMpUserTagService = wxService.getUserTagService();
        try {
            return Result.successData(wxMpUserTagService.tagCreate(name));
        } catch (WxErrorException e) {
            log.error("新增微信用户标签失败", e);
            return Result.errorMessage(e.getMessage());
        }
    }

    @ApiOperation("修改微信用户标签")
    @PutMapping("/update")
    public @ResponseBody Result<Boolean> updateById(@RequestBody JSONObject data) {
        String appId = data.getStr("appId");
        Long id = data.getLong("id");
        String name = data.getStr("name");
        WxMpUserTagService wxMpUserTagService = wxService.getUserTagService();
        try {
            return Result.successData(wxMpUserTagService.tagUpdate(id, name));
        } catch (WxErrorException e) {
            log.error("修改微信用户标签失败", e);
            return Result.errorMessage(e.getMessage());
        }
    }

    @ApiOperation("删除微信用户标签")
    @DeleteMapping("/operate/remove/{id}")
    public @ResponseBody Result<Boolean> removeById(@PathVariable("id") Long id, String appId) {
        WxMpUserTagService wxMpUserTagService = wxService.getUserTagService();
        try {
            return Result.successData(wxMpUserTagService.tagDelete(id));
        } catch (WxErrorException e) {
            log.error("删除微信用户标签失败", e);
            return Result.errorMessage(e.getMessage());
        }
    }
}
