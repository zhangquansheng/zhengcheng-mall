package com.zhengcheng.mall.dto;

import lombok.Data;

/**
 * OrderCreateCmd
 *
 * @author quansheng1.zhang
 * @since 2022/6/23 10:12
 */
@Data
public class OrderCreateCmd {

    /**
    * 用户编号
    */
    private String userNo;
    /**
     * 订单备注
     */
    private String memo;
}
