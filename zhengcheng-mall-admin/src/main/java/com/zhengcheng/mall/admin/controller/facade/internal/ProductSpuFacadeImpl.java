package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.dto.ProductSpuDTO;
import com.zhengcheng.mall.admin.controller.facade.ProductSpuFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.ProductAssembler;
import com.zhengcheng.mall.domain.entity.ProductSpu;
import com.zhengcheng.mall.service.ProductSpuService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

/**
 * ProductSpuFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 22:25
 */
@Service
public class ProductSpuFacadeImpl implements ProductSpuFacade {

    @Autowired
    private ProductSpuService productSpuService;
    @Autowired
    private ProductAssembler  productAssembler;

    @Override
    public PageInfo<ProductSpuDTO> page(PageCommand pageCommand) {
        IPage<ProductSpu> page = productSpuService.page(PageUtil.getPage(pageCommand),
                new LambdaQueryWrapper<ProductSpu>().orderByDesc(ProductSpu::getCreateTime));

        PageInfo<ProductSpuDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(productAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }
}
