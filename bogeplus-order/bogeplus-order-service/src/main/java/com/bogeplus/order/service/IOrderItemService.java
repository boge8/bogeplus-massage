package com.bogeplus.order.service;

import com.bogeplus.order.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单项目中间表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface IOrderItemService extends IService<OrderItem> {

    /**
     * 根据订单id查询项目id
     * @param orderId
     * @return
     */
    List<Long> listItemIdsByOrderId(Long orderId);

}
