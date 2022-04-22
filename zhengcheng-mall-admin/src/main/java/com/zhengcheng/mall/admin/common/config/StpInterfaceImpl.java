package com.zhengcheng.mall.admin.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import cn.dev33.satoken.stp.StpInterface;

/**
 * 自定义权限验证接口扩展
 *
 * @author quansheng1.zhang
 * @since 2022/4/21 17:32
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Lists.newArrayList("sys:role:main", "sys:role:del", "sys:role:enable");
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return new ArrayList<>();
    }
}
