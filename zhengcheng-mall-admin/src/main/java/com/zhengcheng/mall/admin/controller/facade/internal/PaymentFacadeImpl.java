package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.PaymentPageCommand;
import com.zhengcheng.mall.admin.controller.dto.PaymentDTO;
import com.zhengcheng.mall.admin.controller.facade.PaymentFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.PaymentAssembler;
import com.zhengcheng.mall.domain.entity.Payment;
import com.zhengcheng.mall.service.PaymentService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

import cn.hutool.core.util.StrUtil;

/**
 * PaymentFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/20 11:08
 */
@Service
public class PaymentFacadeImpl implements PaymentFacade {

    @Autowired
    private PaymentService   paymentService;
    @Autowired
    private PaymentAssembler paymentAssembler;

    @Override
    public PageInfo<PaymentDTO> page(PaymentPageCommand paymentPageCommand) {
        IPage<Payment> page = paymentService
                .page(PageUtil.getPage(paymentPageCommand),
                        new LambdaQueryWrapper<Payment>().eq(StrUtil.isNotBlank(paymentPageCommand.getTradeNo()),
                                Payment::getTradeNo, paymentPageCommand.getTradeNo())
                                .orderByDesc(Payment::getCreateTime));

        PageInfo<PaymentDTO> pageInfo = PageInfo.empty(paymentPageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(paymentAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }
}
