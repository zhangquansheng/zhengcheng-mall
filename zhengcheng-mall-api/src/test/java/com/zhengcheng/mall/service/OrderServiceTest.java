package com.zhengcheng.mall.service;

import java.util.Date;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sankuai.inf.leaf.common.Result;
import com.sankuai.inf.leaf.common.Status;
import com.zhengcheng.leaf.exception.LeafServerException;
import com.zhengcheng.leaf.exception.NoKeyException;
import com.zhengcheng.leaf.service.SegmentService;
import com.zhengcheng.mall.BaseTest;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * OrderServiceTest
 *
 * @author quansheng1.zhang
 * @since 2022/5/8 12:37
 */
@Slf4j
public class OrderServiceTest extends BaseTest {

    @Autowired
    private OrderService   orderService;
    @Autowired
    private SegmentService segmentService;

    @Rule
    public ContiPerfRule   contiPerfRule = new ContiPerfRule();

    @Test
    @PerfTest(threads = 2, invocations = 2)
    public void batchSave() {
        String id = get("leaf-segment-order", segmentService.getId("leaf-segment-order"));
        orderService.create(StrUtil.format("{}{}{}", DateUtil.format(new Date(), "yyyyMMdd"), id, 3L), 3L, "下单");
    }

    private String get(String key, Result id) {
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
