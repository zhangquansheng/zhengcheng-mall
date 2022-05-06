package com.zhengcheng.mall.api.controller.facade.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zhengcheng.common.dto.UserDTO;
import com.zhengcheng.common.exception.BizException;
import com.zhengcheng.mall.api.command.UserCommand;
import com.zhengcheng.mall.api.controller.facade.UserFacade;
import com.zhengcheng.mall.api.controller.facade.internal.assembler.UserAssembler;
import com.zhengcheng.mall.domain.entity.User;
import com.zhengcheng.mall.domain.entity.UserRole;
import com.zhengcheng.mall.service.RoleAuthorityService;
import com.zhengcheng.mall.service.UserRoleService;
import com.zhengcheng.mall.service.UserService;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 用户(User)外观模式，接口实现
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:49
 */
@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService          userService;
    @Autowired
    private UserRoleService      userRoleService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;
    @Autowired
    private UserAssembler        userAssembler;

    @Override
    public UserDTO findByByToken(String tokenValue) {
        Object loginId = StpUtil.getLoginIdByToken(tokenValue);
        if (Objects.isNull(loginId)) {
            // TODO 专属的登录过期业务异常
            throw new BizException("登录信息已过期！");
        }

        Long id = Long.valueOf(String.valueOf(loginId));
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
    public UserDTO findByUsername(String username) {
        if (StrUtil.isBlank(username)) {
            throw new BizException("用户名不能为空");
        }

        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (Objects.isNull(user)) {
            throw new BizException("未查询到对应的用户信息！");
        }

        return userAssembler.toDTO(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long add(UserCommand userCommand) {
        User user = userAssembler.toEntity(userCommand);
        user.setPassword(userService.rasDecrypt(user.getPassword()));
        userService.save(user);

        List<UserRole> userRoles = new ArrayList<>();
        userCommand.getRoleIds().forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRole.setCreateUserId(userCommand.getUpdateUserId());
            userRole.setUpdateUserId(userCommand.getUpdateUserId());
            userRoles.add(userRole);
        });
        if (CollectionUtils.isNotEmpty(userRoles)) {
            userRoleService.saveBatch(userRoles);
        }
        return user.getId();
    }
}
