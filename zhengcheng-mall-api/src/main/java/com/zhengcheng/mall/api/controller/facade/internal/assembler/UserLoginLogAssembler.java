package com.zhengcheng.mall.api.controller.facade.internal.assembler;

import java.util.List;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.zhengcheng.mall.api.controller.facade.internal.dto.UserLoginLogDTO;
import com.zhengcheng.mall.domain.entity.UserLoginLog;


/**
 * 登录日志表(UserLoginLog)封装类
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 19:49:27
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserLoginLogAssembler {

    @Mappings({
            @Mapping(target = "type",source = "type.value"),
            @Mapping(target = "result",source = "result.value"),
            @Mapping(target = "loginTime",source = "createTime",dateFormat = "yyyy-MM-dd HH:mm:ss"),
    })
    UserLoginLogDTO toDTO(UserLoginLog userLoginLog);

    List<UserLoginLogDTO> toDTOs(List<UserLoginLog> userLoginLogs);
}