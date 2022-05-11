package com.zhengcheng.mall.admin.controller.facade;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.dto.ProductSpuDTO;

/**
 * ProductSpuFacade
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 22:25
 */
public interface ProductSpuFacade {

    /**
     * 分页查询
     *
     * @param pageCommand
     *            分页参数
     * @return 数据查询对象
     */
    PageInfo<ProductSpuDTO> page(PageCommand pageCommand);

}
