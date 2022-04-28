package com.zhengcheng.mall.admin.controller.facade;

import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.UserPageCommand;
import com.zhengcheng.mall.admin.controller.dto.UserDTO;

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

}
