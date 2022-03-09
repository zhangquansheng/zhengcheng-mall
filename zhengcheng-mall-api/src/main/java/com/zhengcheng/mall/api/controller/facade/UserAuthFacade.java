package com.zhengcheng.mall.api.controller.facade;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.api.controller.command.UserAuthCommand;
import com.zhengcheng.mall.api.controller.facade.internal.dto.UserAuthDTO;

/**
 * 用户授权表(UserAuth)表Facade接口
 *
 * @author quansheng1.zhang
 * @since 2021-08-14 17:28:10
 */
public interface UserAuthFacade {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return UserAuthDTO
     */
    UserAuthDTO findById(Long id);

    /**
     * 添加单条数据
     *
     * @param userAuthCommand
     *            数据查询对象
     */
    Long add(UserAuthCommand userAuthCommand);

    /**
     * 更新单条数据
     *
     * @param userAuthCommand
     *            数据查询对象
     */
    Long update(UserAuthCommand userAuthCommand);

    /**
     * 分页查询
     *
     * @param pageCommand
     *            分页参数
     * @return 数据查询对象
     */
    PageInfo<UserAuthDTO> page(PageCommand pageCommand);
}
