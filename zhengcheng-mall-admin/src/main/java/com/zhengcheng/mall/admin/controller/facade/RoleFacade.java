package com.zhengcheng.mall.admin.controller.facade;

import java.util.List;

import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.command.EnableCommand;
import com.zhengcheng.mall.admin.controller.command.RoleAuthorityCommand;
import com.zhengcheng.mall.admin.controller.command.RoleCommand;
import com.zhengcheng.mall.admin.controller.command.RolePageCommand;
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
     * 查询所有启用角色
     * @return RoleDTO
     */
    List<RoleDTO> findAll();

    /**
     * 通过ID删除单条数据
     * @param id
     *          主键
     */
    boolean removeById(Long id);

    /**
     * 批量删除
     * @param ids ID列表
     * @return 是/否
     */
    boolean batchRemove(List<Long> ids);

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
    PageInfo<RoleDTO> page(RolePageCommand rolePageCommand);

    /**
     * 保存角色权限
     * @param roleAuthorityCommand RoleAuthorityCommand
     * @return 是/否
     */
    boolean saveRoleAuthority(RoleAuthorityCommand roleAuthorityCommand);
}
