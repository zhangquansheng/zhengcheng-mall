package com.zhengcheng.mall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cn.hutool.crypto.asymmetric.RSA;

/**
 * MallApiApplication
 *
 * @author quansheng1.zhang
 * @since 2022/3/8 17:36
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MallApiApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MallApiApplication.class).run(args);
    }

    /**
     * 注入BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALY2r5Xg6pUeUgzGPsRVFbc5/CbsTyw1/yuqYWhOnC8WcXC94qGyjBkGOYfg6b1rtpDpCpo44wd7ZRF+HLke7GFQjKrpnrLeoXuLfXC1t0IgRuY6hu5bmiIc0XYBRBdDvnZetqwSK4aibgkbWGBUgUAXrxgjF5jM2A3z8jwB/GGxAgMBAAECgYAIvvygycN1j33qINsWN5+yHd8YpS+yeqltWKFe5tfZuluM/E60N53aFtgZfNyE6MMofUiyspFSV7drTNQNhLD21zsopYXHGDGh7SgprNjzHdl847oiPrLLzgSsdAZ5HhyuUj70zve/hzAF9GvFnUVLtez92zhgTWKM3LXGgLs/WQJBAOlv7qDb68bpbEhUgkJBzuWhJkKHK7LmJvYzQSDJhfe4Za2ExWrXLfywUNIYW+FxLDnzr4yOkbuB9/3Nzh9gwJUCQQDH00yVD8dpj8QqNZmPzT8ti3XtWkAIJsKt8KGoqmRDEFMa3rFUtPp0SB74D/FaoaUriDSC63joEmW+EVo9QQmtAkEAzYeVzN+U+i9u/Uga4AddCOUX8J9epEbrqv15rEqCv5cFPv+VafpHLSJ4JmL4Esg4LlYWDGVCfptyI27eutXO+QJBAIcElMhbYwhGpetHUD1EF540jzgTNRTM69oPr4uBpkFSNHUBZ5yutFQBrzs5uoDcm/fwDlsvtABK/ggRPYIKAfkCQQCLy6Enlimf3BFX4nINHXvanvxcwIuHY3bg3lj/ROPNnJMyi+5gHtGmkP02ZgGA7hKEiHN2WOKIvdCxh95569iF";

    @Bean
    public RSA rsa() {
        return new RSA(PRIVATE_KEY, null);
    }
}
