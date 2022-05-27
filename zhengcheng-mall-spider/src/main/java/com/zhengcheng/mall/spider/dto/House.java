package com.zhengcheng.mall.spider.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * 房
 * <p>
 * XPath selector based on Jsoup. https://github.com/code4craft/xsoup
 * <p>
 * https://code.google.com/p/json-path/
 * <p>
 * 站长工具，正则表达式 http://tool.chinaz.com/regex/
 *
 * @author quansheng1.zhang
 * @since 2021-05-13 11:41:54
 */
@TargetUrl("http://220.178.124.94:8010/fangjia/ws/Detail2.aspx\\?Id=\\d+")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class House implements Serializable {

    private static final long serialVersionUID = 7796513216766051749L;

    /**
     * 楼盘ID
     */
    private Integer           lpId;
    /**
     * 备案号
     */
    private Integer           recordNum;
    /**
     * 名称
     */
    private String            name;
    /**
     * 楼号
     */
    private String            buildingNum;
    /**
     * 建筑面积
     */
    private String            builtUpArea;
    /**
     * 套数
     */
    private Integer           roomNum;

    /**
     * 均价
     */
    private Integer           averagePrice;

    /**
     * 坐落位置
     */
    @ExtractBy(value = "//tr/td/div/span[@id=txtLpLocation]/text()")
    private String            lpLocation;

    /**
     * 所在区域
     */
    @ExtractBy(value = "//tr/td/div/span[@id=txtLpArea]/text()")
    private String            lpArea;

    /**
     * 物业类型
     */
    @ExtractBy(value = "//tr/td/span[@id=txtLpwyType]/text()")
    private String            wyType;

    /**
     * 建筑类型
     */
    @ExtractBy(value = "//tr/td/span[@id=txtLpBuildType]/text()")
    private String            buildType;

    /**
     * 开发企业
     */
    @ExtractBy(value = "//tr/td/span[@id=txtLpkfEnterprise]/text()")
    private String            kfEnterprise;

    /**
     * 设计单位
     */
    @ExtractBy(value = "//tr/td/span[@id=txtLpsjEnterprise]/text()")
    private String            sjEnterprise;

    /**
     * 楼盘物业
     */
    @ExtractBy(value = "//tr/td/div/span[@id=txtLpwyEnterprise]/text()")
    private String            wyEnterprise;

    /**
     * 周边配套
     */
    @ExtractBy(value = "//tr/td/div/span[@id=txtLpRim]/text()")
    private String            rim;

    /**
     * 交通状况
     */
    @ExtractBy(value = "//tr/td/div/span[@id=txtLpTraffic]/text()")
    private String            traffic;
}
