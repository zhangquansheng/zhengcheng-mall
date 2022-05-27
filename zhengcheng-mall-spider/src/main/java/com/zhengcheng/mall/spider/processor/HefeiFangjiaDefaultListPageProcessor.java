package com.zhengcheng.mall.spider.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.mall.spider.dto.House;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * HefeiFangjiaDefaultListPageProcessor
 *
 * @author quansheng1.zhang
 * @since 2022/5/27 21:30
 */
@Slf4j
public class HefeiFangjiaDefaultListPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site        site           = Site.me().setRetryTimes(3).setSleepTime(500);

    private PageCommand pageCommand;

    private List<House> houses;

    /**
     * 当前页
     */
    private Integer     currentPageNum = 1;

    public HefeiFangjiaDefaultListPageProcessor(PageCommand pageCommand, List<House> houses) {
        this.pageCommand = pageCommand;
        this.houses = houses;
    }

    @Override
    public void process(Page page) {
        log.info("当前分页数:{}", currentPageNum);
        // 指定页面，抓取数据
        if (Objects.equals(pageCommand.getPageNo(), currentPageNum)) {
            for (int x = 0; x < 15; x++) {
                String recordNum = page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/a/text()", x, 1)).get();
                if (StrUtil.isNotBlank(recordNum)) {
                    House house = new House();
                    Integer lpId = Integer
                            .parseInt(page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/a/@href", x, 1)).get()
                                    .replace("Detail2.aspx?Id=", "").trim());
                    house.setLpId(lpId);
                    house.setRecordNum(NumberUtil.parseInt(recordNum.trim()));
                    house.setName(page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/a/text()", x, 2)).get().trim());
                    house.setBuildingNum(
                            page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/text()", x, 3)).get().trim());
                    house.setBuiltUpArea(page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/text()", x, 4)).get());
                    house.setRoomNum(Integer.valueOf(
                            page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/text()", x, 5)).get().trim()));
                    house.setAveragePrice(NumberUtil.parseInt(page.getHtml()
                            .xpath(StrUtil.format("//tr[{}]//td[{}]/text()", x, 6)).get().replace(",", "").trim()));
                    log.info("成功抓取：{}", JSONUtil.toJsonStr(house));

                    houses.add(house);
                }
            }
        }

        if (pageCommand.getPageNo() <= 1 || currentPageNum >= pageCommand.getPageNo()) {
            log.info("是第一页或者当前抓取的页面已等于指定的页面，则退出！currentPageNum:{},pageNo:{}", currentPageNum, pageCommand.getPageNo());
            return;
        }
        // 部分三：从页面发现后续的url地址来抓取（非第一页）
        String viewState = page.getHtml().xpath("//input[@id=__VIEWSTATE]/@value").get();
        String eventValidation = page.getHtml().xpath("//input[@id=__EVENTVALIDATION]/@value").get();

        Request request = new Request("http://220.178.124.94:8010/fangjia/ws/DefaultList.aspx");
        request.setMethod(HttpConstant.Method.POST);
        request.addHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36");
        request.setCharset("utf-8");
        HashMap<String, Object> params = new HashMap<>();
        // 跳转到第几页
        params.put("__EVENTARGUMENT", pageCommand.getPageNo());
        params.put("__EVENTTARGET", "AspNetPager1");
        params.put("__VIEWSTATE", viewState);
        params.put("__EVENTVALIDATION", eventValidation);

        request.setRequestBody(HttpRequestBody.form(params, "utf-8"));

        page.addTargetRequest(request);

        // 设置当前页
        currentPageNum = pageCommand.getPageNo();
    }

    @Override
    public Site getSite() {
        return site;
    }
}
