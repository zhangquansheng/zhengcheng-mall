package com.zhengcheng.mall.api.controller.command;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.zhengcheng.common.validation.annotation.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小天才基础表-房(House)数据查询对象
 *
 * @author quansheng1.zhang
 * @since 2021-05-13 11:41:56
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HouseCommand implements Serializable {
    private static final long serialVersionUID = -79843607963622019L;

    @ApiModelProperty("ID，更新时候必填")
    @NotNull(message = "ID不能为空", groups = {Update.class})
    private Long id;

    @ApiModelProperty("备案号")
    private String recordNum;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("楼号")
    private String buildingNum;

    @ApiModelProperty("建筑面积")
    private String builtUpArea;

    @ApiModelProperty("套数")
    private Integer roomNum;

    @ApiModelProperty("均价")
    private String averagePrice;

}