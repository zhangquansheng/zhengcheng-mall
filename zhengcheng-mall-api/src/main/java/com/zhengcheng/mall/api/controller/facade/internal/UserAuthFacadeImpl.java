package com.zhengcheng.mall.api.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.api.command.UserAuthCommand;
import com.zhengcheng.mall.api.controller.facade.UserAuthFacade;
import com.zhengcheng.mall.api.controller.facade.internal.assembler.UserAuthAssembler;
import com.zhengcheng.mall.api.dto.UserAuthDTO;
import com.zhengcheng.mall.domain.entity.UserAuth;
import com.zhengcheng.mall.service.UserAuthService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

/**
 * 用户授权表(UserAuth)外观模式，接口实现
 *
 * @author quansheng1.zhang
 * @since 2021-08-14 17:28:10
 */
@Service
public class UserAuthFacadeImpl implements UserAuthFacade {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private UserAuthAssembler userAuthAssembler;

    @Override
    public UserAuthDTO findById(Long id) {
        return userAuthAssembler.toDTO(userAuthService.getById(id));
    }

    @Override
    public Long add(UserAuthCommand userAuthCommand) {
        UserAuth userAuth = userAuthAssembler.toEntity(userAuthCommand);
        userAuthService.save(userAuth);
        return userAuth.getId();
    }

    @Override
    public Long update(UserAuthCommand userAuthCommand) {
        UserAuth userAuth = userAuthAssembler.toEntity(userAuthCommand);
        userAuthService.updateById(userAuth);
        return userAuth.getId();
    }

    @Override
    public PageInfo<UserAuthDTO> page(PageCommand pageCommand) {
        IPage<UserAuth> page = userAuthService.page(PageUtil.getPage(pageCommand));

        PageInfo<UserAuthDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(userAuthAssembler.toDTOs(page.getRecords()));
        return pageInfo;
    }
}
