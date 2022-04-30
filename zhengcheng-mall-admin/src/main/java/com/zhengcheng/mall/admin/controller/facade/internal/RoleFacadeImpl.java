package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mzt.logapi.starter.annotation.LogRecord;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.common.constants.LogRecordType;
import com.zhengcheng.mall.admin.controller.command.EnableCommand;
import com.zhengcheng.mall.admin.controller.command.RoleCommand;
import com.zhengcheng.mall.admin.controller.dto.RoleDTO;
import com.zhengcheng.mall.admin.controller.facade.RoleFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.RoleAssembler;
import com.zhengcheng.mall.domain.entity.Role;
import com.zhengcheng.mall.service.RoleService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

/**
 * 角色表(Role)外观模式，接口实现
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:19:03
 */
@Service
public class RoleFacadeImpl implements RoleFacade {

    @Autowired
    private RoleService   roleService;
    @Autowired
    private RoleAssembler roleAssembler;

    @Override
    public RoleDTO findById(Long id) {
        return roleAssembler.toDTO(roleService.getById(id));
    }

    @Override
    public void removeById(Long id) {
        roleService.removeById(id);
    }

    @LogRecord(success = "{{#enableCommand.enable ? '启用' : '禁用'}}了角色,更新结果:{{#_ret}}", type = LogRecordType.ROLE, bizNo = "{{#enableCommand.id}}")
    @Override
    public boolean enable(EnableCommand enableCommand) {
        return roleService.update(new LambdaUpdateWrapper<Role>().set(Role::getEnable, enableCommand.isEnable())
                .set(Role::getUpdateUserId, enableCommand.getUpdateUserId()).eq(Role::getId, enableCommand.getId()));
    }

    @Override
    public Long add(RoleCommand roleCommand) {
        Role role = roleAssembler.toEntity(roleCommand);
        roleService.save(role);
        return role.getId();
    }

    @Override
    public Long update(RoleCommand roleCommand) {
        roleService.update(new LambdaUpdateWrapper<Role>().set(Role::getName, roleCommand.getName())
                .set(Role::getDescription, roleCommand.getDescription()).eq(Role::getId, roleCommand.getId()));
        return roleCommand.getId();
    }

    @LogRecord(success = "分页查询", type = LogRecordType.ROLE, bizNo = "角色列表")
    @Override
    public PageInfo<RoleDTO> page(PageCommand pageCommand) {
        IPage<Role> page = roleService.page(PageUtil.getPage(pageCommand),
                new LambdaQueryWrapper<Role>().orderByDesc(Role::getCreateTime));

        PageInfo<RoleDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(roleAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }

}
