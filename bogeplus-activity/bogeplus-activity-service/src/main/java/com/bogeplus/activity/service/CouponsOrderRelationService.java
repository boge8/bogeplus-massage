package com.bogeplus.activity.service;

import com.bogeplus.activity.entity.CouponsOrderRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.common.util.Result;

/**
 * <p>
 * 优惠券和订单的关系表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
public interface CouponsOrderRelationService extends IService<CouponsOrderRelation> {

    Result dispatchForOrder(Long id, Long orderId);
}
