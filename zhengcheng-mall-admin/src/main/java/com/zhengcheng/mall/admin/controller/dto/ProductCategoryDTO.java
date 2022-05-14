package com.zhengcheng.mall.admin.controller.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品分类
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 12:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryDTO implements Serializable {

    private static final long serialVersionUID = 2670960277457745620L;

    private Long              id;

    private String            title;
}
