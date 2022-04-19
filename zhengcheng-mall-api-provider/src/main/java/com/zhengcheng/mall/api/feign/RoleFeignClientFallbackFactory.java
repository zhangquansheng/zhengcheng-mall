package com.zhengcheng.mall.api.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.zhengcheng.common.web.PageCommand;
import com.zhengcheng.common.web.PageInfo;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.command.RoleAuthorityCommand;
import com.zhengcheng.mall.api.command.RoleCommand;
import com.zhengcheng.mall.api.dto.RoleDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * UserFeignClientFallbackFactory
 *
 * @author quansheng1.zhang
 * @since 2022/3/11 11:14
 */
@Slf4j
@Component
public class RoleFeignClientFallbackFactory implements FallbackFactory<RoleFeignClient> {
    @Override
    public RoleFeignClient create(Throwable throwable) {
        return new RoleFeignClient() {
            @Override
            public Result<RoleDTO> findById(Long id) {
                log.error("RoleFeignClient findById fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<Long> add(RoleCommand roleCommand) {
                log.error("RoleFeignClient add fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<Long> update(RoleCommand roleCommand) {
                log.error("RoleFeignClient update fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<PageInfo<RoleDTO>> page(PageCommand pageCommand) {
                log.error("RoleFeignClient page fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<Void> authority(RoleAuthorityCommand roleAuthorityCommand) {
                log.error("RoleFeignClient authority fallback message: {}", throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }
        };
    }
}
