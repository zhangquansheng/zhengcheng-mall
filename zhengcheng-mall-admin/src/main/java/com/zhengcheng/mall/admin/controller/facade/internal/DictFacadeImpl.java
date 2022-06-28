package com.zhengcheng.mall.admin.controller.facade.internal;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mzt.logapi.starter.annotation.LogRecord;
import com.zhengcheng.common.holder.ZcUserContextHolder;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.*;
import com.zhengcheng.mall.admin.controller.dto.DictDataDTO;
import com.zhengcheng.mall.admin.controller.dto.DictTypeDTO;
import com.zhengcheng.mall.admin.controller.facade.DictFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.DictDataAssembler;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.DictTypeAssembler;
import com.zhengcheng.mall.common.constants.LogRecordType;
import com.zhengcheng.mall.domain.entity.DictData;
import com.zhengcheng.mall.domain.entity.DictType;
import com.zhengcheng.mall.service.DictDataService;
import com.zhengcheng.mall.service.DictTypeService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

import cn.hutool.core.util.StrUtil;

/**
 * DictTypeFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 20:34
 */
@Service
public class DictFacadeImpl implements DictFacade {

    @Autowired
    private DictTypeService   dictTypeService;
    @Autowired
    private DictTypeAssembler dictTypeAssembler;
    @Autowired
    private DictDataService   dictDataService;
    @Autowired
    private DictDataAssembler dictDataAssembler;

    @LogRecord(success = "分页查询", type = LogRecordType.DICT, bizNo = "字典类型列表")
    @Override
    public PageInfo<DictTypeDTO> typePage(DictTypePageCommand pageCommand) {
        IPage<DictType> page = dictTypeService.page(PageUtil.getPage(pageCommand), new LambdaQueryWrapper<DictType>()
                .like(StrUtil.isNotBlank(pageCommand.getTypeName()), DictType::getName, pageCommand.getTypeName())
                .orderByDesc(DictType::getCreateTime));

        PageInfo<DictTypeDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(dictTypeAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }

    @LogRecord(success = "分页查询", type = LogRecordType.DICT, bizNo = "字典数据列表")
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

    @Override
    public List<DictTypeDTO> typeList() {
        List<DictType> dictTypeList = dictTypeService.list();
        return dictTypeAssembler.toDTOs(dictTypeList);
    }

    @Override
    public boolean addData(DictDataCommand dictDataCommand) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(dictDataCommand, dictData);
        dictData.setIsDefault(0);
        dictData.setEnable(Boolean.TRUE);
        dictData.setCreateUserId(ZcUserContextHolder.getUserId());
        return dictDataService.save(dictData);
    }

    @Override
    public boolean updateData(DictDataCommand dictDataCommand) {
        return dictDataService.update(new LambdaUpdateWrapper<DictData>()
                .set(DictData::getTypeCode, dictDataCommand.getTypeCode())
                .set(DictData::getName, dictDataCommand.getName()).set(DictData::getValue, dictDataCommand.getValue())
                .set(DictData::getUpdateUserId, ZcUserContextHolder.getUserId())
                .eq(DictData::getId, dictDataCommand.getId()));
    }

    @Override
    public boolean removeData(Long id) {
        return dictDataService.removeById(id);
    }

    @Override
    public boolean batchRemoveData(List<Long> ids) {
        return dictDataService.removeBatchByIds(ids);
    }

    @Override
    public boolean enableData(EnableCommand enableCommand) {
        return dictDataService
                .update(new LambdaUpdateWrapper<DictData>().set(DictData::getEnable, enableCommand.isEnable())
                        .set(DictData::getUpdateUserId, ZcUserContextHolder.getUserId())
                        .eq(DictData::getId, enableCommand.getId()));
    }

    @Override
    public DictDataDTO findDataById(Long id) {
        DictData dictData = dictDataService.getById(id);
        return dictDataAssembler.toDTO(dictData);
    }

    @Override
    public boolean saveType(DictTypeCommand dictTypeCommand) {
        DictType dictType = new DictType();
        BeanUtils.copyProperties(dictTypeCommand, dictType);
        dictType.setEnable(Boolean.TRUE);
        dictType.setCreateUserId(ZcUserContextHolder.getUserId());
        return dictTypeService.save(dictType);
    }

    @Override
    public boolean removeType(Long id) {
        return dictTypeService.removeById(id);
    }

    @Override
    public DictTypeDTO findTypeById(Long id) {
        DictType dictType = dictTypeService.getById(id);
        return dictTypeAssembler.toDTO(dictType);
    }

    @Override
    public boolean updateType(DictTypeCommand dictTypeCommand) {
        return dictTypeService.update(new LambdaUpdateWrapper<DictType>()
                .set(DictType::getCode, dictTypeCommand.getCode()).set(DictType::getName, dictTypeCommand.getName())
                .set(DictType::getDescription, dictTypeCommand.getDescription())
                .set(DictType::getUpdateUserId, ZcUserContextHolder.getUserId())
                .eq(DictType::getId, dictTypeCommand.getId()));
    }

    @Override
    public boolean enableType(EnableCommand enableCommand) {
        return dictTypeService
                .update(new LambdaUpdateWrapper<DictType>().set(DictType::getEnable, enableCommand.isEnable())
                        .set(DictType::getUpdateUserId, ZcUserContextHolder.getUserId())
                        .eq(DictType::getId, enableCommand.getId()));
    }

    @Override
    public boolean batchRemoveType(List<Long> ids) {
        return dictTypeService.removeBatchByIds(ids);
    }

}
