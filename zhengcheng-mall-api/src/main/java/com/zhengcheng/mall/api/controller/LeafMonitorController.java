package com.zhengcheng.mall.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sankuai.inf.leaf.segment.SegmentIDGenImpl;
import com.sankuai.inf.leaf.segment.model.LeafAlloc;
import com.sankuai.inf.leaf.segment.model.SegmentBuffer;
import com.zhengcheng.common.web.Result;
import com.zhengcheng.leaf.model.SegmentBufferView;
import com.zhengcheng.leaf.service.SegmentService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * LeafMonitorController
 *
 * @author quansheng1.zhang
 * @since 2022/5/7 22:07
 */
@Slf4j
@Api(tags = { "美团分布式ID监控接口" })
@RestController
@RequestMapping("/leaf/monitor")
public class LeafMonitorController {

    @Autowired
    private SegmentService segmentService;

    @GetMapping(value = "cache")
    public Result<Map<String, SegmentBufferView>> getCache() {
        Map<String, SegmentBufferView> data = new HashMap<>();
        SegmentIDGenImpl segmentIDGen = segmentService.getIdGen();
        if (segmentIDGen == null) {
            throw new IllegalArgumentException("You should config leaf.segment.enable=true first");
        }
        Map<String, SegmentBuffer> cache = segmentIDGen.getCache();
        for (Map.Entry<String, SegmentBuffer> entry : cache.entrySet()) {
            SegmentBufferView sv = new SegmentBufferView();
            SegmentBuffer buffer = entry.getValue();
            sv.setInitOk(buffer.isInitOk());
            sv.setKey(buffer.getKey());
            sv.setPos(buffer.getCurrentPos());
            sv.setNextReady(buffer.isNextReady());
            sv.setMax0(buffer.getSegments()[0].getMax());
            sv.setValue0(buffer.getSegments()[0].getValue().get());
            sv.setStep0(buffer.getSegments()[0].getStep());

            sv.setMax1(buffer.getSegments()[1].getMax());
            sv.setValue1(buffer.getSegments()[1].getValue().get());
            sv.setStep1(buffer.getSegments()[1].getStep());

            data.put(entry.getKey(), sv);

        }
        log.info("Cache info {}", data);
        return Result.successData(data);
    }

    @GetMapping(value = "db")
    public Result<List<LeafAlloc>> getDb() {
        SegmentIDGenImpl segmentIDGen = segmentService.getIdGen();
        if (segmentIDGen == null) {
            throw new IllegalArgumentException("You should config leaf.segment.enable=true first");
        }
        List<LeafAlloc> items = segmentIDGen.getAllLeafAllocs();
        log.info("DB info {}", items);
        return Result.successData(items);
    }

    /**
     * the output is like this: { "timestamp": "1567733700834(2019-09-06 09:35:00.834)", "sequenceId": "3448",
     * "workerId": "39" }
     */
    @GetMapping(value = "decodeSnowflakeId")
    public Result<Map<String, String>> decodeSnowflakeId(@RequestParam("snowflakeId") String snowflakeIdStr) {
        Map<String, String> map = new HashMap<>();
        try {
            long snowflakeId = Long.parseLong(snowflakeIdStr);

            long originTimestamp = (snowflakeId >> 22) + 1288834974657L;
            Date date = new Date(originTimestamp);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            map.put("timestamp", originTimestamp + "(" + sdf.format(date) + ")");

            long workerId = (snowflakeId >> 12) ^ (snowflakeId >> 22 << 10);
            map.put("workerId", String.valueOf(workerId));

            long sequence = snowflakeId ^ (snowflakeId >> 12 << 12);
            map.put("sequenceId", String.valueOf(sequence));
        } catch (NumberFormatException e) {
            map.put("errorMsg", "snowflake Id反解析发生异常!");
        }
        return Result.successData(map);
    }
}
