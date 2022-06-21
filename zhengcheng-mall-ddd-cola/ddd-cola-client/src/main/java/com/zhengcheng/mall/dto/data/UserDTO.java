package com.zhengcheng.mall.dto.data;

import java.util.List;

import lombok.Data;

/**
 * UserDTO
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 15:45
 */
@Data
public class UserDTO {

    private Long         id;
    private String       userNo;
    private String       username;
    private String       email;
    private String       mobile;
    private String       name;
    private String       avatar;
    private boolean      enable;
    private String       lastLogin;
    private List<String> roleCodes;
    private List<String> authorityCodes;
}
