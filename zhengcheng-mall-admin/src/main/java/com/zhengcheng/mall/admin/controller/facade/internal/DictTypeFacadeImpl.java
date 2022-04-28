package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.dto.DictTypeDTO;
import com.zhengcheng.mall.admin.controller.facade.DictTypeFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.DictTypeAssembler;
import com.zhengcheng.mall.domain.entity.DictType;
import com.zhengcheng.mall.service.DictTypeService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

/**
 * DictTypeFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 20:34
 */
@Component
public class DictTypeFacadeImpl implements DictTypeFacade {

    @Autowired
    private DictTypeService   dictTypeService;
    @Autowired
    private DictTypeAssembler dictTypeAssembler;

    @Override
    public PageInfo<DictTypeDTO> page(PageCommand pageCommand) {
        IPage<DictType> page = dictTypeService.page(PageUtil.getPage(pageCommand),
                new LambdaQueryWrapper<DictType>().orderByDesc(DictType::getCreateTime));

        PageInfo<DictTypeDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(dictTypeAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }
}
