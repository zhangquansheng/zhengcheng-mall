package com.zhengcheng.mall.admin.controller.facade;

import java.util.List;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.DictDataCommand;
import com.zhengcheng.mall.admin.controller.command.DictDataPageCommand;
import com.zhengcheng.mall.admin.controller.dto.DictDataDTO;
import com.zhengcheng.mall.admin.controller.dto.DictTypeDTO;

/**
 * DictTypeFacade
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 20:31
 */
public interface DictFacade {

    /**
     * 分页查询字典类型
     */
    PageInfo<DictTypeDTO> typePage(PageCommand pageCommand);

    /**
     * 分页查询字典
     */
    PageInfo<DictDataDTO> dataPage(DictDataPageCommand dictDataPageCommand);

    /**
     * 查询所有字典类型
     */
    List<DictTypeDTO> typeList();

    /**
     * 新增字典数据
     * @param dictDataCommand DictDataCommand
     * @return ID
     */
    boolean addData(DictDataCommand dictDataCommand);
}
