package com.zhengcheng.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhengcheng.mall.common.dto.UserLoginLogDTO;
import com.zhengcheng.mall.domain.entity.UserLoginLog;

/**
 * 登录日志表(UserLoginLog)表数据库访问层
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 19:49:27
 */
public interface UserLoginLogMapper extends BaseMapper<UserLoginLog> {

    IPage<UserLoginLogDTO> selectPageVo(IPage<?> page);
}
