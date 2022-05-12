package com.zhengcheng.mall.admin.controller.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SkuDataDTO
 *
 * @author quansheng1.zhang
 * @since 2022/5/12 18:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkuDataDTO implements Serializable {

    private static final long serialVersionUID = -5614609694555731233L;

    private String            key;

    private String            propName;

    private String            value;
}
