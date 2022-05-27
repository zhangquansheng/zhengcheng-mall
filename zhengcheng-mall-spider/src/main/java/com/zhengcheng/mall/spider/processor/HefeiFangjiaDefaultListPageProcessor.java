package com.zhengcheng.mall.spider.processor;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.zhengcheng.mall.spider.dto.House;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * HefeiFangjiaDefaultListPageProcessor
 *
 * @author quansheng1.zhang
 * @since 2022/5/27 21:30
 */
@Slf4j
public class HefeiFangjiaDefaultListPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site        site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private List<House> houses;

    public HefeiFangjiaDefaultListPageProcessor(List<House> houses) {
        this.houses = houses;
    }

    @Override
    public void process(Page page) {
        for (int x = 0; x < 15; x++) {
            String recordNum = page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/a/text()", x, 1)).get();
            if (StrUtil.isNotBlank(recordNum)) {
                House house = new House();
                Integer lpId = Integer.parseInt(page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/a/@href", x, 1))
                        .get().replace("Detail2.aspx?Id=", "").trim());
                house.setLpId(lpId);
                house.setRecordNum(NumberUtil.parseInt(recordNum.trim()));
                house.setName(page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/a/text()", x, 2)).get().trim());
                house.setBuildingNum(
                        page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/text()", x, 3)).get().trim());
                house.setBuiltUpArea(page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/text()", x, 4)).get());
                house.setRoomNum(Integer
                        .valueOf(page.getHtml().xpath(StrUtil.format("//tr[{}]//td[{}]/text()", x, 5)).get().trim()));
                house.setAveragePrice(NumberUtil.parseInt(page.getHtml()
                        .xpath(StrUtil.format("//tr[{}]//td[{}]/text()", x, 6)).get().replace(",", "").trim()));
                houses.add(house);
            }
        }

        if (CollectionUtils.isEmpty(houses)) {
            return;
        }
        log.info("成功抓取[{}]条记录", houses.size());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
