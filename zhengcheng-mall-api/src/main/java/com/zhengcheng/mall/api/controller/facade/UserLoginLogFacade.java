package com.zhengcheng.mall.api.controller.facade;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.api.dto.UserLoginLogDTO;

/**
 * 登录日志表(UserLoginLog)表Facade接口
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 19:51:46
 */
public interface UserLoginLogFacade {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return UserLoginLogDTO
     */
    UserLoginLogDTO findById(Long id);

    /**
     * 分页查询
     *
     * @param pageCommand
     *            分页参数
     * @return 数据查询对象
     */
    PageInfo<UserLoginLogDTO> page(PageCommand pageCommand);
}