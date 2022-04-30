package com.zhengcheng.mall.admin.controller.facade;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.EnableCommand;
import com.zhengcheng.mall.admin.controller.command.RoleCommand;
import com.zhengcheng.mall.admin.controller.dto.RoleDTO;

/**
 * 角色表(Role)表Facade接口
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:19:03
 */
public interface RoleFacade {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return RoleDTO
     */
    RoleDTO findById(Long id);

    /**
     * 通过ID删除单条数据
     * @param id
     *          主键
     */
    void removeById(Long id);

    /**
     * 开启/禁用
     * @param enableCommand EnableCommand
     */
    boolean enable(EnableCommand enableCommand);

    /**
     * 添加单条数据
     *
     * @param roleCommand
     *            数据查询对象
     */
    Long add(RoleCommand roleCommand);

    /**
     * 更新单条数据
     *
     * @param roleCommand
     *            数据查询对象
     */
    Long update(RoleCommand roleCommand);

    /**
     * 分页查询
     *
     * @param pageCommand
     *            分页参数
     * @return 数据查询对象
     */
    PageInfo<RoleDTO> page(PageCommand pageCommand);

}
