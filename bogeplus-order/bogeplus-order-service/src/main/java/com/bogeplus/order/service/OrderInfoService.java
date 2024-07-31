package com.bogeplus.order.service;

import com.bogeplus.common.util.Result;
import com.bogeplus.order.controller.RequestBody.OrderInfoRequest;
import com.bogeplus.order.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 订单信息表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface OrderInfoService extends IService<OrderInfo> {
    Result saveOrder(OrderInfoRequest request);

}
