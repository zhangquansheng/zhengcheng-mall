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
@FeignClient(name = "OauthFeign", url = "${zc.mall.api.url}", fallbackFactory = OauthFeignClientFallbackFactory.class)
public interface OauthFeignClient {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    Result<Void> logout();

    @RequestMapping(value = "/logoutByToken", method = RequestMethod.GET)
    Result<Void> logoutByToken(@RequestParam("access_token") String accessToken);

    @GetMapping("/token")
    Result<TokenInfoDTO> getToken(@RequestParam("username") String username,
                                  @RequestParam("enPassword") String enPassword);

    @PostMapping("/token")
    Result<TokenInfoDTO> postToken(@RequestParam("username") String username,
                                   @RequestParam("password") String password);
}
