package com.zhengcheng.mall.user.executor;

import org.springframework.stereotype.Component;

import com.zhengcheng.mall.dto.UserAddCmd;

/**
 * UserAddCmdExe
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 10:36
 */
@Component
public class UserAddCmdExe {

    public Long execute(UserAddCmd cmd) {
        //The flow of usecase is defined here.
        //The core ablility should be implemented in Domain. or sink to Domian gradually

        return null;
    }

}
