package com.zhengcheng.mall.weixin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengcheng.common.web.Result;

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
@RequestMapping("/admin/wxusertags")
public class WxUserTagsController {

    @Autowired
    private WxMpService wxService;

    @ApiOperation("微信用户标签页面")
    @GetMapping("/view")
    public String view() {
        return "/view/system/wxusertags";
    }

    @PostMapping("/list")
    public @ResponseBody Result<List<WxUserTag>> getWxusertags(String appId) {
        WxMpUserTagService wxMpUserTagService = wxService.getUserTagService();
        try {
            List<WxUserTag> listWxUserTag = wxMpUserTagService.tagGet();
            return Result.successData(listWxUserTag);
        } catch (WxErrorException e) {
            log.error("获取微信用户标签失败", e);
            return Result.errorMessage(e.getMessage());
        }
    }
}
