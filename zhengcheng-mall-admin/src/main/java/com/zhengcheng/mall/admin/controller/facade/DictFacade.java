package com.zhengcheng.mall.admin.controller.facade;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
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
}
