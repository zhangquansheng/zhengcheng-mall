package com.zhengcheng.mall.admin.controller.facade.internal;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import com.zhengcheng.mall.admin.common.holder.TokenInfoHolder;
import com.zhengcheng.mall.service.LogRecordService;

import lombok.extern.slf4j.Slf4j;

/**
 * DbLogRecordFacadeImpl
 *
 * @author quansheng1.zhang
 * @since 2022/4/30 19:19
 */
@Slf4j
@Component
public class DbLogRecordFacadeImpl implements ILogRecordService {

    @Autowired
    private LogRecordService logRecordService;

    @Override
    public void record(LogRecord logRecord) {
        log.info("【logRecord】log={}", logRecord);

        com.zhengcheng.mall.domain.entity.LogRecord record = new com.zhengcheng.mall.domain.entity.LogRecord();
        Long userId = TokenInfoHolder.getUserId();
        record.setCreateUserId(userId);
        record.setUpdateUserId(userId);
        BeanUtils.copyProperties(logRecord, record);
        logRecordService.save(record);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return Lists.newArrayList();
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return Lists.newArrayList();
    }
}
