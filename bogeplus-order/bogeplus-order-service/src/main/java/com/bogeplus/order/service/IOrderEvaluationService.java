package com.bogeplus.order.service;

import com.bogeplus.order.entity.OrderEvaluation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 用户对技师评价表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface IOrderEvaluationService extends IService<OrderEvaluation> {

    /**
     * 处理对技师的评价请求
     * @param orderEvaluation
     * @return
     */
    void evaluateTechnicianByOrderId(OrderEvaluation orderEvaluation);

}
