package com.bogeplus.order.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.order.dto.MassagistOrderExtraDTO;
import com.bogeplus.order.dto.MassagistOrderInfoDTO;
import com.bogeplus.order.service.IOrderInfoService;
import com.bogeplus.order.vo.MassagistOrderInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Api(value = "订单信息", tags = {"订单信息相关接口"})
public class OrderInfoController {

    private static final Logger log = LoggerFactory.getLogger(OrderInfoController.class);
    @Autowired
    private IOrderInfoService orderInfoService;

    @GetMapping("/getMassagistOrderInfo")
    @ApiOperation(value = "查询技师订单信息", notes = "查询技师订单信息接口")
    public Result getMassagistOrderInfo(@RequestBody MassagistOrderInfoDTO massagistOrderInfoDTO){
        log.info("查询技师订单信息：{}", massagistOrderInfoDTO);
        List<MassagistOrderInfoVO> list = orderInfoService.listbyMassagistId(massagistOrderInfoDTO);
        return Result.success(list);
    }

    @PutMapping("/confirm/{orderId}")
    @ApiOperation(value = "技师接单", notes = "技师接单接口")
    public Result confirm(@PathVariable Long orderId){
        log.info("技师接单：{}", orderId);
        return orderInfoService.confirm(orderId);
    }

    @PutMapping("/depart/{orderId}")
    @ApiOperation(value = "技师出发", notes = "技师出发接口")
    public Result depart(@PathVariable Long orderId){
        log.info("技师出发：{}", orderId);
        return orderInfoService.depart(orderId);
    }

    @PutMapping("/arrive")
    @ApiOperation(value = "技师到达", notes = "技师到达接口")
    public Result arrive(@RequestBody MassagistOrderExtraDTO massagistOrderExtraDTO) {
        log.info("技师到达：{}", massagistOrderExtraDTO);
        return orderInfoService.arrive(massagistOrderExtraDTO);
    }

    @PutMapping("/startService/{orderId}")
    @ApiOperation(value = "技师开始服务", notes = "技师开始服务接口")
    public Result startService(@PathVariable Long orderId) {
        log.info("技师开始服务：{}", orderId);
        return orderInfoService.startService(orderId);
    }

}
