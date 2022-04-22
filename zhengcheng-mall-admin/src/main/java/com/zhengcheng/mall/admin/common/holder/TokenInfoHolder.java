package com.zhengcheng.mall.admin.common.holder;

import org.springframework.lang.Nullable;

import com.zhengcheng.mall.api.dto.TokenInfoDTO;

/**
 * 登录信息保存
 * 
 * @author :    zhngquansheng
 * @date :    2019/12/20 15:17
 */
public class TokenInfoHolder {

    private static final ThreadLocal<TokenInfoDTO> tokenInfoLocal = new ThreadLocal<>();

    public static TokenInfoDTO getTokenInfo() {
        return tokenInfoLocal.get();
    }

    public static void setTokenInfo(TokenInfoDTO tokenInfo) {
        tokenInfoLocal.set(tokenInfo);
    }

    public static void removeTokenInfo() {
        tokenInfoLocal.remove();
    }

    @Nullable
    public static Long getUserId() {
        TokenInfoDTO tokenInfoDTO = getTokenInfo();
        return tokenInfoDTO == null ? null : Long.valueOf(String.valueOf(tokenInfoDTO.getLoginId()));
    }
}
