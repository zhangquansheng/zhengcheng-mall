package com.zhengcheng.mall.api.controller.facade;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.api.command.UserCommand;
import com.zhengcheng.mall.api.command.UserRoleCommand;
import com.zhengcheng.mall.api.dto.UserDTO;

/**
 * 用户(User)表Facade接口
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:49
 */
public interface UserFacade {

    /**
     * 通过token获取用户消息
     * @param tokenValue 令牌
     * @return UserDTO
     */
    UserDTO findByByToken(String tokenValue);
    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return UserDTO
     */
    UserDTO findById(Long id);

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return UserDTO
     */
    UserDTO findByUsername(String username);

    /**
     * 添加单条数据
     *
     * @param userCommand
     *            数据查询对象
     */
    Long add(UserCommand userCommand);

    /**
     * 分页查询
     *
     * @param pageCommand
     *            分页参数
     * @return 数据查询对象
     */
    PageInfo<UserDTO> page(PageCommand pageCommand);

    /**
     * 添加用户角色
     * 
     * @param userRoleCommand
     *            UserRoleCommand
     */
    void addUserRole(UserRoleCommand userRoleCommand);
}