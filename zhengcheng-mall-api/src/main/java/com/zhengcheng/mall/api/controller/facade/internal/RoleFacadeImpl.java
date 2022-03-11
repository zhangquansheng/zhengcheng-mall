package com.zhengcheng.mall.api.controller.facade.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.api.command.RoleAuthorityCommand;
import com.zhengcheng.mall.api.command.RoleCommand;
import com.zhengcheng.mall.api.controller.facade.RoleFacade;
import com.zhengcheng.mall.api.controller.facade.internal.assembler.RoleAssembler;
import com.zhengcheng.mall.api.dto.RoleDTO;
import com.zhengcheng.mall.domain.entity.Role;
import com.zhengcheng.mall.domain.entity.RoleAuthority;
import com.zhengcheng.mall.service.RoleAuthorityService;
import com.zhengcheng.mall.service.RoleService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

/**
 * 角色表(Role)外观模式，接口实现
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:19:03
 */
@Service
public class RoleFacadeImpl implements RoleFacade {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleAssembler roleAssembler;
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @Override
    public RoleDTO findById(Long id) {
        return roleAssembler.toDTO(roleService.getById(id));
    }

    @Override
    public Long add(RoleCommand roleCommand) {
        Role role = roleAssembler.toEntity(roleCommand);
        roleService.save(role);
        return role.getId();
    }

    @Override
    public Long update(RoleCommand roleCommand) {
        roleService.update(new LambdaUpdateWrapper<Role>().set(Role::getName, roleCommand.getName())
            .set(Role::getDescription, roleCommand.getDescription()).eq(Role::getId, roleCommand.getId()));
        return roleCommand.getId();
    }

    @Override
    public PageInfo<RoleDTO> page(PageCommand pageCommand) {
        IPage<Role> page = roleService.page(PageUtil.getPage(pageCommand),
            new LambdaQueryWrapper<Role>().orderByDesc(Role::getCreateTime));

        PageInfo<RoleDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(roleAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }

    @Override
    public void addRoleAuthority(RoleAuthorityCommand roleAuthorityCommand) {
        List<RoleAuthority> roleAuthorityList = new ArrayList<>();
        roleAuthorityCommand.getAuthorityIds().forEach(authorityId -> roleAuthorityList
            .add(RoleAuthority.builder().authorityId(authorityId).roleId(roleAuthorityCommand.getRoleId()).build()));
        roleAuthorityService.save(roleAuthorityList);
    }
}
