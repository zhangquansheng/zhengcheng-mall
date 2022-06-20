package com.zhengcheng.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.zhengcheng.mall.service.UserActivityStatisticsService;

/**
 * UserActivityStatisticsServiceImpl
 *
 * @author quansheng1.zhang
 * @since 2022/6/20 17:18
 */
@Service
public class UserActivityStatisticsServiceImpl implements UserActivityStatisticsService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Boolean setBit(String key, long offset, boolean value) {
        return stringRedisTemplate.opsForValue().setBit(key, offset, value);
    }

    @Override
    public Boolean getBit(String key, long offset) {
        return stringRedisTemplate.opsForValue().getBit(key, offset);
    }

    @Override
    public Long bitCount(String key) {
        return stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }

    @Override
    public Long bitOpResult(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        byte[][] bytes = new byte[desKey.length][];
        for (int i = 0; i < desKey.length; i++) {
            bytes[i] = desKey[i].getBytes();
        }
        stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(op, saveKey.getBytes(), bytes));

        return bitCount(saveKey);
    }

}
