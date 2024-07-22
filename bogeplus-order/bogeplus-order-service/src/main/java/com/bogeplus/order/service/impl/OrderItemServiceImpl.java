package com.bogeplus.order.service.impl;

import com.bogeplus.order.entity.OrderItem;
import com.bogeplus.order.mapper.OrderItemMapper;
import com.bogeplus.order.service.IOrderItemService;
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
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {

}
