package com.zhengcheng.mall.dto;

import com.alibaba.cola.dto.Query;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * UserByNoQry
 *
 * @author quansheng1.zhang
 * @since 2022/6/21 15:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserByNoQry extends Query {
    private static final long serialVersionUID = -6228321727172682286L;

    private String            userNo;
}
