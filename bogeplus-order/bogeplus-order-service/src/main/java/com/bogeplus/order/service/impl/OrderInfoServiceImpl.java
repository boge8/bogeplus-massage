package com.bogeplus.order.service.impl;

import com.bogeplus.order.entity.OrderInfo;
import com.bogeplus.order.mapper.OrderInfoMapper;
import com.bogeplus.order.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.order.vo.OrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public OrderDetailVO getOrderDetail(Long orderId) {
        OrderDetailVO orderDetail = orderInfoMapper.getOrderDetail(orderId);
        // 根据订单状态进行不同的数据处理
        switch (orderDetail.getStatus()) {
            case 3: // 待接单
                // 不返回接单时间、出发时间、到达时间、服务开始时间、服务结束时间
                orderDetail.setTakeTime(null);
                orderDetail.setDepartureTime(null);
                orderDetail.setArrivalTime(null);
                orderDetail.setServiceStartTime(null);
                orderDetail.setServiceEndTime(null);
                break;
            case 4: // 已接单
                // 不返回出发时间、到达时间、服务开始时间、服务结束时间
                orderDetail.setDepartureTime(null);
                orderDetail.setArrivalTime(null);
                orderDetail.setServiceStartTime(null);
                orderDetail.setServiceEndTime(null);
                break;
            case 5: // 技师出发
                // 不返回到达时间、服务开始时间、服务结束时间
                orderDetail.setArrivalTime(null);
                orderDetail.setServiceStartTime(null);
                orderDetail.setServiceEndTime(null);
                break;
            case 6: // 技师到达
                // 不返回服务开始时间、服务结束时间
                orderDetail.setServiceStartTime(null);
                orderDetail.setServiceEndTime(null);
                break;
            case 7: // 开始服务
                // 不返回服务结束时间
                orderDetail.setServiceEndTime(null);
                break;
            case 8: // 服务完成
                // 所有时间都返回
                break;
            default:
                // 其他状态，不返回任何时间
                orderDetail.setTakeTime(null);
                orderDetail.setDepartureTime(null);
                orderDetail.setArrivalTime(null);
                orderDetail.setServiceStartTime(null);
                orderDetail.setServiceEndTime(null);
                break;
        }

        return orderDetail;
    }
}
