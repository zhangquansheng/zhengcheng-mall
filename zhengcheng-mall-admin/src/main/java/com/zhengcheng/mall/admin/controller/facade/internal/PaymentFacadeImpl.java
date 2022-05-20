package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.stereotype.Service;

import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.PaymentPageCommand;
import com.zhengcheng.mall.admin.controller.dto.PaymentDTO;
import com.zhengcheng.mall.admin.controller.facade.PaymentFacade;

/**
 * PaymentFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/20 11:08
 */
@Service
public class PaymentFacadeImpl implements PaymentFacade {
    @Override
    public PageInfo<PaymentDTO> page(PaymentPageCommand paymentPageCommand) {
        return null;
    }
}
