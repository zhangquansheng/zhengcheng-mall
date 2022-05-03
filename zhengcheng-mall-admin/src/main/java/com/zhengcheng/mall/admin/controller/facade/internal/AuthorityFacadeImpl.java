package com.zhengcheng.mall.admin.controller.facade.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mzt.logapi.starter.annotation.LogRecord;
import com.zhengcheng.common.exception.BizException;
import com.zhengcheng.mall.admin.controller.command.AuthorityCommand;
import com.zhengcheng.mall.admin.controller.command.EnableCommand;
import com.zhengcheng.mall.admin.controller.dto.AuthorityDTO;
import com.zhengcheng.mall.admin.controller.dto.TreeselectDTO;
import com.zhengcheng.mall.admin.controller.facade.AuthorityFacade;
import com.zhengcheng.mall.admin.controller.facade.internal.assembler.AuthorityAssembler;
import com.zhengcheng.mall.common.constants.LogRecordType;
import com.zhengcheng.mall.domain.entity.Authority;
import com.zhengcheng.mall.service.AuthorityService;

import cn.hutool.core.collection.CollectionUtil;

/**
 * 权限表(Authority)外观模式，接口实现
 *
 * @author quansheng1.zhang
 * @since 2021-08-13 14:46:58
 */
@Service
public class AuthorityFacadeImpl implements AuthorityFacade {

    @Autowired
    private AuthorityService   authorityService;
    @Autowired
    private AuthorityAssembler authorityAssembler;

    @LogRecord(success = "查询", type = LogRecordType.AUTHORITY, bizNo = "权限列表")
    @Override
    public List<AuthorityDTO> findAll() {
        return authorityAssembler.toDTOs(authorityService
                .list(new LambdaQueryWrapper<Authority>().orderBy(Boolean.TRUE, Boolean.TRUE, Authority::getSort)));
    }

    @Override
    public AuthorityDTO findById(Long id) {
        return authorityAssembler.toDTO(authorityService.getById(id));
    }

    @LogRecord(success = "新增了权限：{{#authorityCommand.name}}", type = LogRecordType.AUTHORITY, subType = LogRecordType.ADD_SUB_TYPE, bizNo = "{{#authorityCommand.code}}")
    @Override
    public Long add(AuthorityCommand authorityCommand) {
        Authority authority = authorityAssembler.toEntity(authorityCommand);
        authorityService.save(authority);
        return authority.getId();
    }

    @LogRecord(success = "更新了权限{_DIFF{#authorityCommand}}", type = LogRecordType.AUTHORITY, subType = LogRecordType.UPDATE_SUB_TYPE, bizNo = "{{#authorityCommand.id}}")
    @Override
    public Long update(AuthorityCommand authorityCommand) {
        Authority authority = authorityAssembler.toEntity(authorityCommand);
        authorityService.update(new LambdaUpdateWrapper<Authority>().set(Authority::getName, authorityCommand.getName())
                .set(Authority::getRoute, authorityCommand.getRoute())
                .set(Authority::getIcon, authorityCommand.getIcon()).set(Authority::getUrl, authorityCommand.getUrl())
                .set(Authority::getRemark, authorityCommand.getRemark())
                .set(Authority::getType, authorityCommand.getType()).set(Authority::getSort, authorityCommand.getSort())
                .eq(Authority::getId, authorityCommand.getId()));
        return authority.getId();
    }

    @LogRecord(success = "{{#enableCommand.enable ? '启用' : '禁用'}}了权限,更新结果:{{#_ret}}", type = LogRecordType.ROLE, bizNo = "{{#enableCommand.id}}")
    @Override
    public boolean enable(EnableCommand enableCommand) {
        return authorityService
                .update(new LambdaUpdateWrapper<Authority>().set(Authority::getEnable, enableCommand.isEnable())
                        .set(Authority::getUpdateUserId, enableCommand.getUpdateUserId())
                        .eq(Authority::getId, enableCommand.getId()));
    }

    @LogRecord(success = "删除权限", type = LogRecordType.AUTHORITY, subType = LogRecordType.DELETE_SUB_TYPE, bizNo = "{{#id}}")
    @Override
    public boolean deleteById(Long id) {
        List<Authority> authorityList = authorityService.list(
                new LambdaQueryWrapper<Authority>().eq(Authority::getPid, id).eq(Authority::getEnable, Boolean.TRUE)
                        .orderBy(Boolean.TRUE, Boolean.TRUE, Authority::getSort));
        if (CollectionUtil.isNotEmpty(authorityList)) {
            throw new BizException("此权限下存在子权限，无法删除！");
        }
        return authorityService.removeById(id);
    }

    @Override
    public List<TreeselectDTO> findTreeselectList() {
        List<Authority> authorityList = authorityService.list(new LambdaQueryWrapper<Authority>()
                .eq(Authority::getEnable, Boolean.TRUE).orderBy(Boolean.TRUE, Boolean.TRUE, Authority::getSort));
        return findChildren(authorityList, 0L);
    }

    private List<TreeselectDTO> findChildren(List<Authority> authorityList, Long pid) {
        List<TreeselectDTO> children = new ArrayList<>();
        authorityList.forEach(authority -> {
            if (authority.getPid().equals(pid)) {
                TreeselectDTO treeselectDTO = new TreeselectDTO();
                treeselectDTO.setId(authority.getId());
                treeselectDTO.setName(authority.getName());
                treeselectDTO.setChecked(false);
                treeselectDTO.setChildren(findChildren(authorityList, authority.getId()));
                treeselectDTO
                        .setOpen(CollectionUtil.isEmpty(treeselectDTO.getChildren()) ? Boolean.FALSE : Boolean.TRUE);
                children.add(treeselectDTO);
            }
        });
        return children;
    }
}
