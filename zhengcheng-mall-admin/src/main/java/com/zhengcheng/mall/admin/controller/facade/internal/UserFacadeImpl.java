package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.UserPageCommand;
import com.zhengcheng.mall.admin.controller.facade.UserFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.UserAssembler;
import com.zhengcheng.mall.api.dto.UserDTO;
import com.zhengcheng.mall.domain.entity.User;
import com.zhengcheng.mall.service.UserService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

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
    private UserService   userService;
    @Autowired
    private UserAssembler userAssembler;

    @Override
    public UserDTO findById(Long id) {
        return userAssembler.toDTO(userService.getById(id));
    }

    @Override
    public PageInfo<UserDTO> page(UserPageCommand userPageCommand) {
        IPage<User> page = userService.page(PageUtil.getPage(userPageCommand), new LambdaQueryWrapper<User>()
                .eq(StrUtil.isNotBlank(userPageCommand.getUsername()), User::getUsername, userPageCommand.getUsername())
                .eq(StrUtil.isNotBlank(userPageCommand.getName()), User::getName, userPageCommand.getName())
                .eq(StrUtil.isNotBlank(userPageCommand.getMobile()), User::getName, userPageCommand.getMobile()));

        PageInfo<UserDTO> pageInfo = PageInfo.empty(userPageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(userAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }

}
