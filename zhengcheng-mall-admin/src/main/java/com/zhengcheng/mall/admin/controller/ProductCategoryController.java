package com.zhengcheng.mall.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.dto.ProductCategoryDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ProductCategoryController
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 12:06
 */
@Api(tags = { "商品分类管理" })
@Controller
@RequestMapping("/admin/productCategory")
public class ProductCategoryController {

    @ApiOperation("查询所有商品分类数据")
    @GetMapping("/data")
    public @ResponseBody Result<List<ProductCategoryDTO>> data() {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setId(1L);
        productCategoryDTO.setTitle("服装");
        return Result.successData(Lists.newArrayList(productCategoryDTO));
    }

}
