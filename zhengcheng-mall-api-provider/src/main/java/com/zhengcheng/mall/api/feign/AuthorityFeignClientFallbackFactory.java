package com.zhengcheng.mall.api.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.AuthorityCommand;
import com.zhengcheng.mall.api.dto.AuthorityDTO;
import com.zhengcheng.mall.api.dto.TreeNodeDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * AuthorityFeignClientFallbackFactory
 *
 * @author quansheng1.zhang
 * @since 2022/4/21 9:40
 */
@Slf4j
@Component
public class AuthorityFeignClientFallbackFactory implements FallbackFactory<AuthorityFeignClient> {

    @Override
    public AuthorityFeignClient create(Throwable throwable) {
        return new AuthorityFeignClient() {
            @Override
            public Result<List<TreeNodeDTO>> tree() {
                log.error("AuthorityFeignClient tree fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<List<AuthorityDTO>> data() {
                log.error("AuthorityFeignClient data fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<AuthorityDTO> findById(Long id) {
                log.error("AuthorityFeignClient findById fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<Long> add(AuthorityCommand authorityCommand) {
                log.error("AuthorityFeignClient add fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<Long> update(AuthorityCommand authorityCommand) {
                log.error("AuthorityFeignClient update fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }
        };
    }
}
