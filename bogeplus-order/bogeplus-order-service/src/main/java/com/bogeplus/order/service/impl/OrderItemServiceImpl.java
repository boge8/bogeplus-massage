package com.bogeplus.order.service.impl;

import com.bogeplus.order.dto.OrderItemDTO;
import com.bogeplus.order.entity.OrderItem;
import com.bogeplus.order.mapper.OrderItemMapper;
import com.bogeplus.order.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单项目中间表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

    @Override
    public boolean saveOrderItem(OrderItemDTO dto) {
        OrderItem orderItem = OrderItem.nil();        //初始化订单项目关系对象
        orderItem.setOrderId(dto.getOrderId());
        orderItem.setItemId(dto.getItemId());
        orderItem.setQuantity(dto.getQuantity());
        return save(orderItem);
    }
}
