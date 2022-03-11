package com.zhengcheng.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhengcheng.mall.domain.entity.User;

/**
 * 用户(User)表服务接口
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:49
 */
public interface UserService extends IService<User> {

    boolean isSamePassword(String password, String encodedPassword);

    /**
     * 解密
     * @param enPassword 秘文
     * @return 明文
     */
    String rasDecrypt(String enPassword);
}