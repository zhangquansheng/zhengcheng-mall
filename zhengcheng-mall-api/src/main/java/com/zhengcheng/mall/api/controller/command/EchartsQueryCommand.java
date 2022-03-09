package com.zhengcheng.mall.api.controller.command;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EchartsQueryCommand
 *
 * @author quansheng1.zhang
 * @since 2021/5/26 10:32
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EchartsQueryCommand {

    /**
     * echarts类型
     */
    private String type;
    /**
     * 查询分组类型
     */
    @NotNull(message = "查询分组类型不能为空")
    private Integer groupType;

    /**
     * 年区间查询
     */
    private String yearRange;

    /**
     * 查询开始年份
     */
    private Integer startYear;

    /**
     * 查询结束年份
     */
    private Integer endYear;
}
