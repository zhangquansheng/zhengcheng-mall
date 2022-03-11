package com.zhengcheng.mall.api.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.api.command.UserRoleCommand;
import com.zhengcheng.mall.api.controller.facade.UserRoleFacade;
import com.zhengcheng.mall.api.controller.facade.internal.assembler.UserRoleAssembler;
import com.zhengcheng.mall.api.dto.UserRoleDTO;
import com.zhengcheng.mall.domain.entity.UserRole;
import com.zhengcheng.mall.service.UserRoleService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

/**
 * 用户角色表(UserRole)外观模式，接口实现
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:26:58
 */
@Service
public class UserRoleFacadeImpl implements UserRoleFacade {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserRoleAssembler userRoleAssembler;

    @Override
    public UserRoleDTO findById(Long id) {
        return userRoleAssembler.toDTO(userRoleService.getById(id));
    }

    @Override
    public Long add(UserRoleCommand userRoleCommand) {
        UserRole userRole = userRoleAssembler.toEntity(userRoleCommand);
        userRoleService.save(userRole);
        return userRole.getId();
    }

    @Override
    public Long update(UserRoleCommand userRoleCommand) {
        UserRole userRole = userRoleAssembler.toEntity(userRoleCommand);
        userRoleService.updateById(userRole);
        return userRole.getId();
    }

    @Override
    public PageInfo<UserRoleDTO> page(PageCommand pageCommand) {
        IPage<UserRole> page = userRoleService.page(PageUtil.getPage(pageCommand));

        PageInfo<UserRoleDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(userRoleAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }
}
