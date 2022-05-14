package com.zhengcheng.mall.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * PasswordRsaUtil
 *
 * @author quansheng1.zhang
 * @since 2022/5/13 18:28
 */
@Service
public class PasswordRsaUtil {

    @Value("${user.password.private-key}")
    private String privateKey;

    /**
     * 解密
     */
    public String decrypt(String enPassword) {
        RSA rsa = new RSA(privateKey, null);
        byte[] decrypt = rsa.decrypt(Base64.decode(enPassword), KeyType.PrivateKey);
        return new String(decrypt);
    }
}
