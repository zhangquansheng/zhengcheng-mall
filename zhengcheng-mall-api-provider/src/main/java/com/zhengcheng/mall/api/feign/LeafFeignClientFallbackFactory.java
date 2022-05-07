package com.zhengcheng.mall.api.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.zhengcheng.common.web.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * LeafFeignClientFallbackFactory
 *
 * @author quansheng1.zhang
 * @since 2022/5/7 22:30
 */
@Slf4j
@Component
public class LeafFeignClientFallbackFactory implements FallbackFactory<LeafFeignClient> {
    @Override
    public LeafFeignClient create(Throwable throwable) {
        return new LeafFeignClient() {
            @Override
            public Result<String> getSegmentId(String key) {
                log.error("LeafFeignClient getSegmentId fallback key: {} , message: {}", key, throwable.getMessage(),
                        throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<String> getSnowflakeId(String key) {
                log.error("LeafFeignClient getSnowflakeId fallback key: {} , message: {}", key, throwable.getMessage(),
                        throwable);
                return Result.fallbackResult();
            }
        };
    }
}
