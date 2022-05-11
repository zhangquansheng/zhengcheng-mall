package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhengcheng.mall.admin.controller.command.SpecificationCommand;
import com.zhengcheng.mall.admin.controller.dto.SpecificationDTO;
import com.zhengcheng.mall.admin.controller.facade.SpecificationFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.SpecificationAssembler;
import com.zhengcheng.mall.domain.entity.Specification;
import com.zhengcheng.mall.service.SpecificationService;

/**
 * SpecificationFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 15:38
 */
@Service
public class SpecificationFacadeImpl implements SpecificationFacade {

    @Autowired
    private SpecificationService   specificationService;
    @Autowired
    private SpecificationAssembler specificationAssembler;

    @Override
    public boolean removeById(Long id) {
        return specificationService.removeById(id);
    }

    @Override
    public SpecificationDTO add(SpecificationCommand specificationCommand) {
        Specification specification = specificationAssembler.toEntity(specificationCommand);
        specificationService.save(specification);
        return specificationAssembler.toDTO(specification);
    }
}
