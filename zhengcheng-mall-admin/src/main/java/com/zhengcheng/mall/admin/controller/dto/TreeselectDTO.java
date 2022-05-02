package com.zhengcheng.mall.admin.controller.dto;

import java.util.List;

import lombok.Data;

/**
 * https://gitee.com/wujiawei0926/treeselect
 *
 * @author quansheng1.zhang
 * @since 2022/5/2 17:16
 */
@Data
public class TreeselectDTO {

    private Long                id;

    private String              name;

    private boolean             open;

    private boolean             checked;

    private List<TreeselectDTO> children;
}
