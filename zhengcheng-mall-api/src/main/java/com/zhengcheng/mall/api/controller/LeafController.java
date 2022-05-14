package com.zhengcheng.mall.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.zhengcheng.leaf.exception.LeafServerException;
import com.zhengcheng.leaf.exception.NoKeyException;
import com.zhengcheng.leaf.service.SegmentService;
import com.zhengcheng.leaf.service.SnowflakeService;
import com.zhengcheng.mall.api.feign.LeafFeignClient;

import io.swagger.annotations.Api;

/**
 * LeafController
 *
 * @author quansheng1.zhang
 * @since 2022/5/7 22:07
 */
@Api(tags = { "美团分布式ID生成接口" })
@RestController
@RequestMapping("/leaf")
public class LeafController implements LeafFeignClient {

    @Autowired
    private SegmentService   segmentService;
    @Autowired
    private SnowflakeService snowflakeService;

    @Override
    @GetMapping(value = "/api/segment/get/{key}")
    public com.zhengcheng.common.web.Result<String> getSegmentId(@PathVariable("key") String key) {
        return com.zhengcheng.common.web.Result.successData(get(key, segmentService.getId(key)));
    }

    @Override
    @GetMapping(value = "/api/snowflake/get/{key}")
    public com.zhengcheng.common.web.Result<String> getSnowflakeId(@PathVariable("key") String key) {
        return com.zhengcheng.common.web.Result.successData(get(key, snowflakeService.getId(key)));
    }

    private String get(@PathVariable("key") String key, Result id) {
        Result result;
        if (key == null || key.isEmpty()) {
            throw new NoKeyException();
        }
        result = id;
        if (result.getStatus().equals(Status.EXCEPTION)) {
            throw new LeafServerException(result.toString());
        }
        return String.valueOf(result.getId());
    }

}
