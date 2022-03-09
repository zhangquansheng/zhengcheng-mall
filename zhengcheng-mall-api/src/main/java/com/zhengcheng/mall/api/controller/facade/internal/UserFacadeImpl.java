package com.zhengcheng.mall.api.controller.facade.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.api.controller.command.UserCommand;
import com.zhengcheng.mall.api.controller.command.UserRoleCommand;
import com.zhengcheng.mall.api.controller.facade.UserFacade;
import com.zhengcheng.mall.api.controller.facade.internal.assembler.UserAssembler;
import com.zhengcheng.mall.api.controller.facade.internal.dto.UserDTO;
import com.zhengcheng.mall.domain.entity.User;
import com.zhengcheng.mall.domain.entity.UserRole;
import com.zhengcheng.mall.service.RoleAuthorityService;
import com.zhengcheng.mall.service.UserRoleService;
import com.zhengcheng.mall.service.UserService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

/**
 * 用户(User)外观模式，接口实现
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:49
 */
@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private UserAssembler userAssembler;

    @Override
    public UserDTO findCurrent(Long id) {
        UserDTO userDTO = this.findById(id);
        userDTO.setRoleCodes(userRoleService.getRoleCodeList(id));
        userDTO.setAuthorityCodes(roleAuthorityService.getAuthorityCodeList(id));
        return userDTO;
    }

    @Override
    public UserDTO findById(Long id) {
        return userAssembler.toDTO(userService.getById(id));
    }

    @Override
    public Long add(UserCommand userCommand) {
        User user = userAssembler.toEntity(userCommand);
        userService.save(user);
        return user.getId();
    }

    @Override
    public PageInfo<UserDTO> page(PageCommand pageCommand) {
        IPage<User> page = userService.page(PageUtil.getPage(pageCommand));

        PageInfo<UserDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(userAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }

    @Override
    public void addUserRole(UserRoleCommand userRoleCommand) {
        List<UserRole> userRoles = new ArrayList<>();
        userRoleCommand.getRoleIds().forEach(
            roleId -> userRoles.add(UserRole.builder().roleId(roleId).userId(userRoleCommand.getUserId()).build()));
        userRoleService.save(userRoles);
    }
}