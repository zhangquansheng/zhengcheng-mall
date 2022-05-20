package com.zhengcheng.mall.admin.controller.facade;

import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.PaymentPageCommand;
import com.zhengcheng.mall.admin.controller.dto.PaymentDTO;

/**
 * PaymentFacade
 *
 * @author quansheng1.zhang
 * @since 2022/5/20 11:07
 */
public interface PaymentFacade {

    /**
     * 分页查询
     *
     * @param paymentPageCommand
     *            分页参数
     * @return 数据查询对象
     */
    PageInfo<PaymentDTO> page(PaymentPageCommand paymentPageCommand);
}
