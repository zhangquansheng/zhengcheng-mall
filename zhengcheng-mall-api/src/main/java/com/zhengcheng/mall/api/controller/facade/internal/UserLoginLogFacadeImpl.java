package com.zhengcheng.mall.api.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.api.controller.facade.UserLoginLogFacade;
import com.zhengcheng.mall.api.controller.facade.internal.assembler.UserLoginLogAssembler;
import com.zhengcheng.mall.api.dto.UserLoginLogDTO;
import com.zhengcheng.mall.domain.entity.UserLoginLog;
import com.zhengcheng.mall.service.UserLoginLogService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

/**
 * 登录日志表(UserLoginLog)外观模式，接口实现
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 19:51:46
 */
@Service
public class UserLoginLogFacadeImpl implements UserLoginLogFacade {

    @Autowired
    private UserLoginLogService userLoginLogService;
    @Autowired
    private UserLoginLogAssembler userLoginLogAssembler;

    @Override
    public UserLoginLogDTO findById(Long id) {
        return userLoginLogAssembler.toDTO(userLoginLogService.getById(id));
    }

    @Override
    public PageInfo<UserLoginLogDTO> page(PageCommand pageCommand) {
        IPage<UserLoginLog> page = userLoginLogService.page(PageUtil.getPage(pageCommand),
            new LambdaQueryWrapper<UserLoginLog>().orderByDesc(UserLoginLog::getCreateTime));

        PageInfo<UserLoginLogDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(userLoginLogAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }
}