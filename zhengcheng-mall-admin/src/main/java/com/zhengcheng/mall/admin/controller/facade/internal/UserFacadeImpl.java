package com.zhengcheng.mall.admin.controller.facade.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mzt.logapi.starter.annotation.LogRecord;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.common.constants.LogRecordType;
import com.zhengcheng.mall.admin.common.interceptor.LoginInterceptor;
import com.zhengcheng.mall.admin.controller.command.LoginSubmitCommand;
import com.zhengcheng.mall.admin.controller.command.UserPageCommand;
import com.zhengcheng.mall.admin.controller.dto.MenuDTO;
import com.zhengcheng.mall.admin.controller.facade.UserFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.UserAssembler;
import com.zhengcheng.mall.api.dto.TokenInfoDTO;
import com.zhengcheng.mall.api.dto.UserDTO;
import com.zhengcheng.mall.api.feign.OauthFeignClient;
import com.zhengcheng.mall.domain.entity.Authority;
import com.zhengcheng.mall.domain.entity.User;
import com.zhengcheng.mall.domain.enums.AuthorityTypeEnum;
import com.zhengcheng.mall.service.AuthorityService;
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
    private UserService      userService;
    @Autowired
    private AuthorityService authortiyService;
    @Autowired
    private UserAssembler    userAssembler;
    @Autowired
    private OauthFeignClient oauthFeign;

    @Override
    public UserDTO findById(Long id) {
        return userAssembler.toDTO(userService.getById(id));
    }

    @LogRecord(success = "分页查询", type = LogRecordType.USER, bizNo = "用户列表")
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

    @Override
    public List<MenuDTO> menu(Long userId) {
        List<Authority> authorities = authortiyService.getAuthorityList(userId);
        // 查询 pid=0 的目录
        List<Authority> catalogues = authorities.stream()
                .filter(s -> (s.getPid() == 0 && AuthorityTypeEnum.CATALOGUE.equals(s.getType())))
                .collect(Collectors.toList());
        List<MenuDTO> menuDTOs = new ArrayList<>();
        catalogues.forEach(catalogue -> {
            MenuDTO menuDTO = this.toMenuDTO(catalogue);
            // 获取目录下的菜单
            List<Authority> menus = authorities.stream()
                    .filter(s -> (s.getPid().equals(catalogue.getId()) && AuthorityTypeEnum.MENU.equals(s.getType())))
                    .collect(Collectors.toList());
            List<MenuDTO> children = new ArrayList<>();
            menus.forEach(menu -> children.add(this.toMenuDTO(menu)));
            menuDTO.setChildren(children);
            menuDTOs.add(menuDTO);
        });
        return menuDTOs;
    }

    @Override
    public Result<TokenInfoDTO> login(LoginSubmitCommand loginSubmitCommand, HttpSession session,
                                      HttpServletRequest request) {
        Result<TokenInfoDTO> result = oauthFeign.postToken(loginSubmitCommand.getUsername(),
                loginSubmitCommand.getEnPassword());
        if (result.hasData()) {
            // 设置当前登录人的用户名
            TokenInfoDTO tokenInfoDTO = result.getData();
            UserDTO userDTO = findById(Long.parseLong(String.valueOf(tokenInfoDTO.getLoginId())));
            tokenInfoDTO.setCurrentUser(userDTO);

            session.setAttribute(LoginInterceptor.PRINCIPAL_ATTRIBUTE_NAME, tokenInfoDTO);
        }
        return result;
    }

    private MenuDTO toMenuDTO(Authority authority) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(authority.getId());
        menuDTO.setTitle(authority.getName());
        menuDTO.setIcon(authority.getIcon());
        menuDTO.setType(authority.getType().getValue());
        menuDTO.setOpenType(AuthorityTypeEnum.MENU.equals(authority.getType()) ? "_iframe" : "");
        menuDTO.setHref(authority.getUrl());
        return menuDTO;
    }

}
