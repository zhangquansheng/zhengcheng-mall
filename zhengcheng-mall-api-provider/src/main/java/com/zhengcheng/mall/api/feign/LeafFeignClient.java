package com.zhengcheng.mall.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zhengcheng.common.web.Result;

/**
 * LeafFeignClient
 *
 * @author quansheng1.zhang
 * @since 2022/5/7 22:29
 */
@FeignClient(name = LeafFeignClient.NAME, fallbackFactory = LeafFeignClientFallbackFactory.class)
public interface LeafFeignClient {
    /**
     * Nacos 对服务名大小写敏感
     */
    String NAME = "zhengcheng-mall";

    @GetMapping(value = "/leaf/api/segment/get/{key}")
    Result<String> getSegmentId(@PathVariable("key") String key);

    @GetMapping(value = "/leaf/api/snowflake/get/{key}")
    Result<String> getSnowflakeId(@PathVariable("key") String key);
}
