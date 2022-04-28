package com.zhengcheng.mall.admin.controller.facade;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.dto.DictTypeDTO;

/**
 * DictTypeFacade
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 20:31
 */
public interface DictTypeFacade {

    /**
     * 查询所有字典类型
     */
    PageInfo<DictTypeDTO> page(PageCommand pageCommand);
}
