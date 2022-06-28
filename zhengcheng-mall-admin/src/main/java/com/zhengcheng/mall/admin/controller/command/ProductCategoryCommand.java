package com.zhengcheng.mall.admin.controller.command;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.zhengcheng.common.validation.annotation.Update;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 添加/更新商品分类
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:46:58
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCategoryCommand implements Serializable {
    private static final long serialVersionUID = 808453051183645421L;

    @ApiModelProperty("ID，更新时候必填")
    @NotNull(message = "ID不能为空", groups = { Update.class })
    private Long              id;

    @ApiModelProperty("名称")
    @NotNull(message = "名称不能为空")
    private String            name;

    @ApiModelProperty("父ID")
    private Long              pid;

    @ApiModelProperty("排序")
    private Integer           sort;

}
