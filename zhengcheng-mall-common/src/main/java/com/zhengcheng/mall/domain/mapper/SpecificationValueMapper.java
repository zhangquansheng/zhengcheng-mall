package com.zhengcheng.mall.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhengcheng.mall.domain.entity.SpecificationValue;

/**
 * SpecificationValueMapper
 *
 * @author quansheng1.zhang
 * @since 2022/5/11 13:34
 */
public interface SpecificationValueMapper extends BaseMapper<SpecificationValue> {

    /**
     * 查询spu的规格值
     * @param spuId spuId
     * @return 规格值列表
     */
    List<Long> findSpecificationValueBySpuId(@Param("spuId") Long spuId);
}
