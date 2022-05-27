package com.zhengcheng.mall.spider.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.spider.dto.House;
import com.zhengcheng.mall.spider.processor.HefeiFangjiaDefaultListPageProcessor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import us.codecraft.webmagic.Spider;

/**
 * SpiderController
 *
 * @author quansheng1.zhang
 * @since 2022/5/27 21:16
 */
@Api(tags = { "爬虫接口" })
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @ApiOperation("抓取")
    @PostMapping("/run")
    public Result<List<House>> run(@Valid @RequestBody PageCommand pageCommand) {
        List<House> houses = new ArrayList<>();
        Spider.create(new HefeiFangjiaDefaultListPageProcessor(pageCommand, houses))
                .addUrl("http://220.178.124.94:8010/fangjia/ws/DefaultList.aspx")
                // 开启5个线程抓取
                .thread(1)
                // 启动爬虫
                .run();
        return Result.successData(houses);
    }

}
