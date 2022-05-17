package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.ProductSkuPageCommand;
import com.zhengcheng.mall.admin.controller.dto.ProductSkuDTO;
import com.zhengcheng.mall.admin.controller.facade.ProductSkuFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.ProductAssembler;
import com.zhengcheng.mall.domain.entity.ProductSku;
import com.zhengcheng.mall.service.ProductSkuService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * ProductSkuFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/17 11:27
 */
@Slf4j
@Service
public class ProductSkuFacadeImpl implements ProductSkuFacade {

    @Autowired
    private ProductSkuService productSkuService;
    @Autowired
    private ProductAssembler  productAssembler;

    @Override
    public PageInfo<ProductSkuDTO> page(ProductSkuPageCommand productSkuPageCommand) {
        IPage<ProductSku> page = productSkuService.page(PageUtil.getPage(productSkuPageCommand),
                new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getSpuId, productSkuPageCommand.getSpuId()));

        PageInfo<ProductSkuDTO> pageInfo = PageInfo.empty(productSkuPageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(productAssembler.toSkuDTOs(page.getRecords()));
        return pageInfo;
    }
}
