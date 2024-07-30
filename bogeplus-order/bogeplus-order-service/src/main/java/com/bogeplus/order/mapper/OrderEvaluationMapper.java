package com.bogeplus.order.mapper;

import com.bogeplus.order.entity.OrderEvaluation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;

/**
 * <p>
 * 用户对技师评价表 Mapper 接口
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface OrderEvaluationMapper extends BaseMapper<OrderEvaluation> {

    /**
     * 根据 技师 id，查询总评分
     *
     * @param id
     * @return
     */
    BigDecimal querySumRatingById(Long id);
}
