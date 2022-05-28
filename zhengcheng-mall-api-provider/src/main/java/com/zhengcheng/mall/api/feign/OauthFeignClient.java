package com.zhengcheng.mall.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhengcheng.common.web.Result;
import com.zhengcheng.mall.api.dto.TokenInfoDTO;

/**
 * OauthFeign
 *
 * @author quansheng1.zhang
 * @since 2022/3/9 18:41
 */
@FeignClient(name = OauthFeignClient.NAME, fallbackFactory = OauthFeignClientFallbackFactory.class)
public interface OauthFeignClient {
    /**
     * Nacos 对服务名大小写敏感
     */
    String NAME = "zhengcheng-mall-api";

    @GetMapping(value = "/oauth/logoutByToken")
    Result<Void> logoutByToken(@RequestParam("token") String token);

    @GetMapping("/oauth/token")
    Result<TokenInfoDTO> getToken(@RequestParam("username") String username,
                                  @RequestParam("enPassword") String enPassword);

    @PostMapping("/oauth/token")
    Result<TokenInfoDTO> postToken(@RequestParam("username") String username,
                                   @RequestParam("password") String password);
}
