package com.zhengcheng.mall.service;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhengcheng.mall.BaseTest;
import com.zhengcheng.mall.domain.entity.Order;

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
    private OrderService orderService;

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @Test
    @PerfTest(threads = 2, invocations = 2)
    public void batchSave() {
        orderService.save(new Order());
    }
}
