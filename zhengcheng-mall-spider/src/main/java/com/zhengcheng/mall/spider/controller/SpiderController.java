package com.zhengcheng.mall.spider.controller;

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
    public Result<Void> run() {
        return Result.success();
    }

}
