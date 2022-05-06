package com.zhengcheng.mall.api.command;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 用户(User)数据查询对象
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:50
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCommand extends com.zhengcheng.common.web.UserCommand {
    private static final long serialVersionUID = -58148876498682370L;

    @ApiModelProperty("用户名")
    private String            username;
    @ApiModelProperty("邮箱")
    private String            email;
    @ApiModelProperty("手机号")
    private String            mobile;
    @ApiModelProperty("姓名")
    private String            name;
    @ApiModelProperty("密码")
    private String            password;
    @ApiModelProperty("头像")
    private String            avatar;
    @ApiModelProperty("是否启用")
    private boolean           enable;
    @ApiModelProperty("角色ID列表")
    private List<Long>        roleIds;
}
