package com.zhengcheng.mall.common.command;

import java.util.List;

import com.zhengcheng.common.web.UserCommand;

import lombok.*;

/**
 * ProductSpuDTO
 *
 * @author quansheng1.zhang
 * @since 2022/5/12 20:37
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductSpuCommand extends UserCommand {
    private static final long       serialVersionUID = 5062390494790183810L;
    /**
     * SPU ID
     */
    private Long                    id;

    /**
     * 商品规格模式 0单规格 1多规格
     */
    private Integer                 specificationMode;
    /**
     * 商品分类
     */
    private Long                    productCategoryId;
    /**
     * SKU 列表
     */
    private List<ProductSkuCommand> skus;
}
