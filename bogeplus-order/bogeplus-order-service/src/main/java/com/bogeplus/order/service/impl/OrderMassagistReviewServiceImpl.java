package com.bogeplus.order.service.impl;

import com.bogeplus.order.entity.OrderMassagistReview;
import com.bogeplus.order.mapper.OrderMassagistReviewMapper;
import com.bogeplus.order.service.IOrderMassagistReviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 技师对客户评价表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Service
public class OrderMassagistReviewServiceImpl extends ServiceImpl<OrderMassagistReviewMapper, OrderMassagistReview> implements IOrderMassagistReviewService {
    @Autowired
    OrderMassagistReviewMapper orderMassagistReviewMapper;

    /**
     * 新增技师对客户评
     * @param orderMassagistReview
     */
    @Override
    public void insertMassagistReview(OrderMassagistReview orderMassagistReview) {
        orderMassagistReviewMapper.insert(orderMassagistReview);
    }
}