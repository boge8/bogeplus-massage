package com.bogeplus.order.service;

import com.bogeplus.order.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.order.vo.OrderDetailVO;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 订单信息表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface IOrderInfoService extends IService<OrderInfo> {
    @ApiOperation(value = "获取订单详情", notes = "根据订单ID获取订单详情")
    OrderDetailVO getOrderDetail(Long orderId);

}
