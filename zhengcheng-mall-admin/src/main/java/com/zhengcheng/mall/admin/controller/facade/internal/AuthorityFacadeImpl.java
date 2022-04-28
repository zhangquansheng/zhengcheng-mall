package com.zhengcheng.mall.admin.controller.facade.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zhengcheng.mall.admin.controller.command.AuthorityCommand;
import com.zhengcheng.mall.admin.controller.dto.AuthorityDTO;
import com.zhengcheng.mall.admin.controller.facade.AuthorityFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.AuthorityAssembler;
import com.zhengcheng.mall.domain.entity.Authority;
import com.zhengcheng.mall.service.AuthorityService;

/**
 * 权限表(Authority)外观模式，接口实现
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:46:58
 */
@Service
public class AuthorityFacadeImpl implements AuthorityFacade {

    @Autowired
    private AuthorityService   authorityService;
    @Autowired
    private AuthorityAssembler authorityAssembler;

    @Override
    public List<AuthorityDTO> findAll() {
        return authorityAssembler.toDTOs(authorityService.list());
    }

    @Override
    public AuthorityDTO findById(Long id) {
        return authorityAssembler.toDTO(authorityService.getById(id));
    }

    @Override
    public Long add(AuthorityCommand authorityCommand) {
        Authority authority = authorityAssembler.toEntity(authorityCommand);
        authorityService.save(authority);
        return authority.getId();
    }

    @Override
    public Long update(AuthorityCommand authorityCommand) {
        Authority authority = authorityAssembler.toEntity(authorityCommand);
        authorityService.update(new LambdaUpdateWrapper<Authority>().set(Authority::getName, authorityCommand.getName())
                .set(Authority::getRoute, authorityCommand.getRoute())
                .set(Authority::getIcon, authorityCommand.getIcon()).set(Authority::getUrl, authorityCommand.getUrl())
                .set(Authority::getRemark, authorityCommand.getRemark())
                .set(Authority::getType, authorityCommand.getType()).set(Authority::getSort, authorityCommand.getSort())
                .eq(Authority::getId, authorityCommand.getId()));
        return authority.getId();
    }

}
