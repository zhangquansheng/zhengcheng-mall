package com.zhengcheng.mall.admin.controller.facade.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.mall.admin.controller.facade.UserLoginLogFacade;
import com.zhengcheng.mall.common.dto.UserLoginLogDTO;
import com.zhengcheng.mall.domain.enums.LoginResultEnum;
import com.zhengcheng.mall.domain.enums.LoginTypeEnum;
import com.zhengcheng.mall.service.UserLoginLogService;
import com.zhengcheng.mybatis.plus.utils.PageUtil;

/**
 * UserLoginLogFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/5/1 22:02
 */
@Service
public class UserLoginLogFacadeImpl implements UserLoginLogFacade {

    @Autowired
    private UserLoginLogService userLoginLogService;

    @Override
    public PageInfo<UserLoginLogDTO> page(PageCommand pageCommand) {
        IPage<UserLoginLogDTO> page = userLoginLogService.selectPageVo(PageUtil.getPage(pageCommand));
        page.getRecords().forEach(userLoginLogDTO -> {
            userLoginLogDTO.setResultDesc(LoginResultEnum.getByValue(userLoginLogDTO.getResult()).getDesc());
            userLoginLogDTO.setTypeDesc(LoginTypeEnum.getByValue(userLoginLogDTO.getType()).getDesc());
        });

        PageInfo<UserLoginLogDTO> pageInfo = PageInfo.empty(pageCommand);
        pageInfo.setTotal(page.getTotal());
        pageInfo.setRecords(page.getRecords());
        return pageInfo;
    }
}
