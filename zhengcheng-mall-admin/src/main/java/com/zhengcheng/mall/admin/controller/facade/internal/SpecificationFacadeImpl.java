package com.zhengcheng.mall.admin.controller.facade.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.zhengcheng.mall.admin.controller.command.SpecificationCommand;
import com.zhengcheng.mall.admin.controller.dto.AttrSpecDTO;
import com.zhengcheng.mall.admin.controller.dto.SpecificationDTO;
import com.zhengcheng.mall.admin.controller.facade.SpecificationFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.SpecificationAssembler;
import com.zhengcheng.mall.domain.entity.Specification;
import com.zhengcheng.mall.domain.entity.SpecificationValue;
import com.zhengcheng.mall.service.SpecificationService;
import com.zhengcheng.mall.service.SpecificationValueService;

import cn.hutool.core.util.StrUtil;

/**
 * SpecificationFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 15:38
 */
@Service
public class SpecificationFacadeImpl implements SpecificationFacade {

    @Autowired
    private SpecificationService      specificationService;
    @Autowired
    private SpecificationValueService specificationValueService;
    @Autowired
    private SpecificationAssembler    specificationAssembler;

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

    @Override
    public AttrSpecDTO findAttrSpec(Long spuId, Long productCategoryId) {
        List<Specification> specifications = specificationService.list(
                new LambdaQueryWrapper<Specification>().eq(Specification::getProductCategoryId, productCategoryId));
        List<SpecificationValue> specificationValues = specificationValueService
                .list(new LambdaQueryWrapper<SpecificationValue>().in(SpecificationValue::getSpecificationId,
                        specifications.stream().map(Specification::getId).collect(Collectors.toList())));
        Map<Long, List<SpecificationValue>> specificationValueMap = specificationValues.stream()
                .collect(Collectors.groupingBy(SpecificationValue::getSpecificationId));

        List<AttrSpecDTO.Spec> specs = new ArrayList<>();
        specifications.forEach(specification -> {
            AttrSpecDTO.Spec spec = new AttrSpecDTO.Spec();
            spec.setId(StrUtil.toString(specification.getId()));
            spec.setTitle(specification.getName());
            // TODO 设置商品已经选中的规格值
            spec.setValue(Lists.newArrayList("5", "6", "9"));

            List<SpecificationValue> specificationValueList = specificationValueMap.get(specification.getId());
            List<AttrSpecDTO.Spec.Option> options = new ArrayList<>();
            specificationValueList.forEach(specificationValue -> options.add(AttrSpecDTO.Spec.Option.builder()
                    .id(String.valueOf(specificationValue.getId())).title(specificationValue.getName()).build()));

            spec.setOptions(options);

            specs.add(spec);
        });
        return AttrSpecDTO.builder().spec(specs).attribute(new ArrayList<>()).build();
    }
}
