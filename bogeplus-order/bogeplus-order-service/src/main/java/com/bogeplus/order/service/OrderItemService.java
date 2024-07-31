package com.bogeplus.order.service;

import com.bogeplus.order.dto.OrderItemDTO;
import com.bogeplus.order.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单项目中间表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface OrderItemService extends IService<OrderItem> {
    boolean saveOrderItem(OrderItemDTO dto);
}
