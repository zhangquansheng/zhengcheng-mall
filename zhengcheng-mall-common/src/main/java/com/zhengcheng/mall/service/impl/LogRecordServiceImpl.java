package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.LogRecord;
import com.zhengcheng.mall.domain.mapper.LogRecordMapper;
import com.zhengcheng.mall.service.LogRecordService;

/**
 * LogRecordServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/4/30 19:18
 */
@Service
public class LogRecordServiceImpl extends ServiceImpl<LogRecordMapper, LogRecord> implements LogRecordService {
}
