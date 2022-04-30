package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.DictDataPageCommand;
import com.zhengcheng.mall.admin.controller.dto.DictDataDTO;
import com.zhengcheng.mall.admin.controller.dto.DictTypeDTO;
import com.zhengcheng.mall.admin.controller.facade.DictFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.DictDataAssembler;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.DictTypeAssembler;
import com.zhengcheng.mall.domain.entity.DictData;
import com.zhengcheng.mall.domain.entity.DictType;
import com.zhengcheng.mall.service.DictDataService;
import com.zhengcheng.mall.service.DictTypeService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

/**
 * DictTypeFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 20:34
 */
@Component
public class DictFacadeImpl implements DictFacade {

    @Autowired
    private DictTypeService   dictTypeService;
    @Autowired
    private DictTypeAssembler dictTypeAssembler;
    @Autowired
    private DictDataService   dictDataService;
    @Autowired
    private DictDataAssembler dictDataAssembler;

    @Override
    public PageInfo<DictTypeDTO> typePage(PageCommand pageCommand) {
        IPage<DictType> page = dictTypeService.page(PageUtil.getPage(pageCommand),
                new LambdaQueryWrapper<DictType>().orderByDesc(DictType::getCreateTime));

        PageInfo<DictTypeDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(dictTypeAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }

    @Override
    public PageInfo<DictDataDTO> dataPage(DictDataPageCommand dictDataPageCommand) {
        IPage<DictData> page = dictDataService.page(PageUtil.getPage(dictDataPageCommand),
                new LambdaQueryWrapper<DictData>().eq(DictData::getTypeCode, dictDataPageCommand.getTypeCode())
                        .orderByDesc(DictData::getCreateTime));

        PageInfo<DictDataDTO> pageInfo = PageInfo.empty(dictDataPageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(dictDataAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }
}