package com.bogeplus.order.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.order.controller.RequestBody.OrderInfoRequest;
import com.bogeplus.order.entity.OrderInfo;
import com.bogeplus.order.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Api(tags = "订单信息",value = "订单信息相关接口" )
@Slf4j
@RestController
@RequestMapping("/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation(value = "保存订单信息",notes = "保存订单信息")
    @PostMapping("/saveOrder")
    public Result<OrderInfo> saveOrder(OrderInfoRequest request){
        return orderInfoService.saveOrder(request);
    }
}
