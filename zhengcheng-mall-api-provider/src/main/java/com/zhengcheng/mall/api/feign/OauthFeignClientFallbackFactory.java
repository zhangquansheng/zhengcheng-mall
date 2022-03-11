package com.zhengcheng.mall.api.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.dto.TokenInfoDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * OauthFeignFallbackFactory
 *
 * @author quansheng1.zhang
 * @since 2022/3/10 11:02
 */
@Slf4j
@Component
public class OauthFeignClientFallbackFactory implements FallbackFactory<OauthFeignClient> {
    @Override
    public OauthFeignClient create(Throwable throwable) {
        return new OauthFeignClient() {
            @Override
            public Result<Void> logoutByToken(String accessToken) {
                log.error("OauthFeignClient logoutByToken fallback accessToken: {} , message: {}", accessToken, throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<TokenInfoDTO> getToken(String username, String enPassword) {
                log.error("OauthFeignClient getToken fallback username: {} , message: {}", username, throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }

            @Override
            public Result<TokenInfoDTO> postToken(String username, String password) {
                log.error("OauthFeignClient postToken fallback username: {} , message: {}", username, throwable.getMessage(), throwable);
                return Result.fallbackResult();
            }
        };
    }
}
