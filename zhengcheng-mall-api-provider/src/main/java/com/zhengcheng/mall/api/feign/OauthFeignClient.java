package com.zhengcheng.mall.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
    String NAME = "zhengcheng-mall";

    @RequestMapping(value = "/oauth/logoutByToken", method = RequestMethod.GET)
    Result<Void> logoutByToken(@RequestParam("access_token") String accessToken);

    @GetMapping("/oauth/token")
    Result<TokenInfoDTO> getToken(@RequestParam("username") String username,
                                  @RequestParam("enPassword") String enPassword);

    @PostMapping("/oauth/token")
    Result<TokenInfoDTO> postToken(@RequestParam("username") String username,
                                   @RequestParam("password") String password);
}
