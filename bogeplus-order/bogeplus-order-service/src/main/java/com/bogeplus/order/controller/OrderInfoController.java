package com.bogeplus.order.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.order.service.IOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@RestController
@RequestMapping("/orderInfo")
@Api(value = "订单信息管理", tags = "订单信息")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;


    /**
     * 修改订单状态
     * @param orderNum
     * @param status
     * @return
     */
    @ApiOperation(value = "修改订单状态", notes = "根据订单编号和新的状态码修改订单状态")
    @PutMapping("/{orderNum}/{status}")
    public Result updateOrderStatus(@PathVariable("orderNum") String orderNum, @PathVariable("status")Integer status){
        return Result.status(orderInfoService.updateOrderStatus(orderNum, status));
    }

}