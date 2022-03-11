package com.zhengcheng.mall.api.controller.facade;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.api.command.UserRoleCommand;
import com.zhengcheng.mall.api.dto.UserRoleDTO;

/**
 * 用户角色表(UserRole)表Facade接口
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:26:58
 */
public interface UserRoleFacade {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return UserRoleDTO
     */
    UserRoleDTO findById(Long id);

    /**
     * 添加单条数据
     *
     * @param userRoleCommand
     *            数据查询对象
     */
    Long add(UserRoleCommand userRoleCommand);

    /**
     * 更新单条数据
     *
     * @param userRoleCommand
     *            数据查询对象
     */
    Long update(UserRoleCommand userRoleCommand);

    /**
     * 分页查询
     *
     * @param pageCommand
     *            分页参数
     * @return 数据查询对象
     */
    PageInfo<UserRoleDTO> page(PageCommand pageCommand);
}
