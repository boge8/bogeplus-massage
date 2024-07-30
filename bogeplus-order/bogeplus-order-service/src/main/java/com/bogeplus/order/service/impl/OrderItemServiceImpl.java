package com.bogeplus.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bogeplus.order.entity.OrderItem;
import com.bogeplus.order.mapper.OrderItemMapper;
import com.bogeplus.order.service.IOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 根据订单id查询项目id
     * @param orderId
     * @return
     */
    @Override
    public List<Long> listItemIdsByOrderId(Long orderId) {
        //构造查询条件
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<OrderItem>()
                .select("item_id")
                .eq("order_id",orderId);
        //查询
        List<OrderItem> orderItemList = orderItemMapper.selectList(queryWrapper);

        List<Long> list = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            list.add(orderItem.getItemId());
        }

        return list;
    }
}
