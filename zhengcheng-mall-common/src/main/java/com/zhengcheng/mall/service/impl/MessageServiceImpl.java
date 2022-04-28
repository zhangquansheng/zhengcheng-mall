package com.zhengcheng.mall.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.domain.entity.Message;
import com.zhengcheng.mall.domain.mapper.MessageMapper;
import com.zhengcheng.mall.service.MessageService;

/**
 * MessageServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/4/28 22:16
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
