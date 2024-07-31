package com.bogeplus.order.controller;

import com.bogeplus.order.service.IOrderInfoService;
import com.bogeplus.order.vo.OrderDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@RestController
@RequestMapping("/order/orderInfo")
@Api(tags = "订单详情状态查询接口")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService iOrderInfoService;

    @ApiOperation(value = "获取订单详情", notes = "根据订单ID获取订单详情")
    @GetMapping("/detail/{orderId}")
    public OrderDetailVO getOrderDetail(@PathVariable Long orderId) {
        return iOrderInfoService.getOrderDetail(orderId);
    }

}
