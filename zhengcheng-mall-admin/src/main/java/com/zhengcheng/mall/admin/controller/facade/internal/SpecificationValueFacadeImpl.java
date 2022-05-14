package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengcheng.mall.admin.controller.command.SpecificationValueCommand;
import com.zhengcheng.mall.admin.controller.dto.SpecificationValueDTO;
import com.zhengcheng.mall.admin.controller.facade.SpecificationValueFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.SpecificationValueAssembler;
import com.zhengcheng.mall.domain.entity.SpecificationValue;
import com.zhengcheng.mall.service.SpecificationValueService;

/**
 * SpecificationValueFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 14:38
 */
@Service
public class SpecificationValueFacadeImpl implements SpecificationValueFacade {

    @Autowired
    private SpecificationValueService   specificationValueService;
    @Autowired
    private SpecificationValueAssembler specificationValueAssembler;

    @Override
    public boolean removeById(Long id) {
        return specificationValueService.removeById(id);
    }

    @Override
    public SpecificationValueDTO add(SpecificationValueCommand specificationValueCommand) {
        SpecificationValue specificationValue = specificationValueAssembler.toEntity(specificationValueCommand);
        specificationValueService.save(specificationValue);
        return specificationValueAssembler.toDTO(specificationValue);
    }

}
