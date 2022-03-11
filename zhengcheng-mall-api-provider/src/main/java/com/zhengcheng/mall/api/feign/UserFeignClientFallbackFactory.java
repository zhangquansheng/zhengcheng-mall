package com.zhengcheng.mall.api.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.UserCommand;
import com.zhengcheng.mall.api.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * UserFeignClientFallbackFactory
 *
 * @author quansheng1.zhang
 * @since 2022/3/11 11:14
 */
@Slf4j
@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public Result<UserDTO> findByUsername(String username) {
                log.error("UserFeignClient findByUsername fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<UserDTO> current() {
                log.error("UserFeignClient current fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<Long> add(UserCommand userCommand) {
                log.error("UserFeignClient add fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }
        };
    }
}
