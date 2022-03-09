package com.zhengcheng.mall.api.controller.command;

import com.zhengcheng.common.web.PageCommand;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * HousePageCommand
 *
 * @author quansheng1.zhang
 * @since 2021/5/27 13:39
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HousePageCommand extends PageCommand {

    @ApiModelProperty("所在区域")
    private String lpArea;
    @ApiModelProperty("楼盘名称")
    private String name;
    @ApiModelProperty("品牌物业")
    private String wyEnterprise;
}
