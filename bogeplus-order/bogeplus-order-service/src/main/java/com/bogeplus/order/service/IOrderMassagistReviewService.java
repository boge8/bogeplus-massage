package com.bogeplus.order.service;

import com.bogeplus.order.entity.OrderMassagistReview;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 技师对客户评价表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface IOrderMassagistReviewService extends IService<OrderMassagistReview> {

    /**
     * 新增技师对客户评
     * @param orderMassagistReview
     */
    void insertMassagistReview(OrderMassagistReview orderMassagistReview);
}
