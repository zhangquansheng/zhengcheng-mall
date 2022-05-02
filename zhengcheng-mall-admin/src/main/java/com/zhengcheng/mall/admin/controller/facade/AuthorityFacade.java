package com.zhengcheng.mall.admin.controller.facade;

import java.util.List;

import com.zhengcheng.mall.admin.controller.command.AuthorityCommand;
import com.zhengcheng.mall.admin.controller.dto.AuthorityDTO;
import com.zhengcheng.mall.admin.controller.dto.TreeselectDTO;

/**
 * 权限表(Authority)表Facade接口
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:46:58
 */
public interface AuthorityFacade {

    /**
     * 查询所有权限
     */
    List<AuthorityDTO> findAll();

    /**
     * 通过ID查询单条数据
     *
     * @param id
     *            主键
     * @return AuthorityDTO
     */
    AuthorityDTO findById(Long id);

    /**
     * 添加单条数据
     *
     * @param authorityCommand
     *            数据查询对象
     */
    Long add(AuthorityCommand authorityCommand);

    /**
     * 更新单条数据
     *
     * @param authorityCommand
     *            数据查询对象
     */
    Long update(AuthorityCommand authorityCommand);

    /**
     * 根据ID删除
     * @param id ID
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询 Treeselect 
     * @return TreeselectDTO
     */
    List<TreeselectDTO> findTreeselectList();

}
