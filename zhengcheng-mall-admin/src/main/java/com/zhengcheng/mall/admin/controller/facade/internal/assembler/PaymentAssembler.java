package com.zhengcheng.mall.admin.controller.facade.internal.assembler;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.zhengcheng.mall.admin.controller.dto.PaymentDTO;
import com.zhengcheng.mall.domain.entity.Payment;

/**
 * PaymentAssembler
 *
 * @author quansheng1.zhang
 * @since 2022/5/20 11:17
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PaymentAssembler {

    @Mappings({ @Mapping(target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "updateTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "successTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "expiredTime", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "type", source = "type.value"), @Mapping(target = "typeName", source = "type.name"),
            @Mapping(target = "method", source = "method.value"),
            @Mapping(target = "methodName", source = "method.name"),
            @Mapping(target = "status", source = "status.value"),
            @Mapping(target = "statusName", source = "status.name"), })
    PaymentDTO toDTO(Payment payment);

    List<PaymentDTO> toDTOs(List<Payment> payments);

}
