package com.zhengcheng.mall.admin.controller.facade;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.admin.controller.command.LoginSubmitCommand;
import com.zhengcheng.mall.admin.controller.command.UserPageCommand;
import com.zhengcheng.mall.admin.controller.dto.MenuDTO;
import com.zhengcheng.mall.api.dto.TokenInfoDTO;
import com.zhengcheng.mall.api.dto.UserDTO;

/**
 * 用户(User)表Facade接口
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:49
 */
public interface UserFacade {
    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return UserDTO
     */
    UserDTO findById(Long id);

    /**
     * 分页查询
     *
     * @param userPageCommand
     *            分页参数
     * @return 数据查询对象
     */
    PageInfo<UserDTO> page(UserPageCommand userPageCommand);

    /**
     * 查询用户的菜单
     * @param userId 用户ID
     * @return 菜单
     */
    List<MenuDTO> menu(Long userId);

    /**
     * 用户登录
     */
    Result<TokenInfoDTO> login(LoginSubmitCommand loginSubmitCommand, HttpSession session);
}
