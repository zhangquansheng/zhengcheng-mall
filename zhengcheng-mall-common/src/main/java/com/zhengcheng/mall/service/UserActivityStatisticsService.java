package com.zhengcheng.mall.service;

import org.springframework.data.redis.connection.RedisStringCommands;

/**
 * 用户活跃度统计
 *
 * @author quansheng1.zhang
 * @since 2022/6/20 17:16
 */
public interface UserActivityStatisticsService {

    /**
     * 将指定offset偏移量的值设置为1；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @param value  true：即该位设置为1，否则设置为0
     * @return 返回设置该value之前的值。
     */
    Boolean setBit(String key, long offset, boolean value);

    /**
     * 将指定offset偏移量的值设置为0；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @return 若偏移位上的值为1，那么返回true。
     */
    Boolean getBit(String key, long offset);

    /**
     * 统计对应的bitmap上value为1的数量
     *
     * @param key bitmap的key
     * @return value等于1的数量
     */
    Long bitCount(String key);

    /**
     * 对一个或多个保存二进制的字符串key进行元操作，并将结果保存到saveKey上，并返回统计之后的结果。
     *
     * @param op      元操作类型；
     * @param saveKey 元操作后将结果保存到saveKey所在的结构中。
     * @param desKey  需要进行元操作的类型。
     * @return 返回saveKey结构上value=1的所有数量值。
     */
    Long bitOpResult(RedisStringCommands.BitOperation op, String saveKey, String... desKey);
}
