package com.bogeplus.order.controller;

import com.bogeplus.common.util.Result;
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
    @ApiOperation(value = "获取技师订单信息", notes = "获取技师订单信息接口")
    public Result<List<MassagistOrderInfoVO>> getMassagistOrderInfo(@RequestBody MassagistOrderInfoDTO massagistOrderInfoDTO){
        log.info("获取技师订单信息：{}", massagistOrderInfoDTO);
        List<MassagistOrderInfoVO> list = orderInfoService.listbyMassagistId(massagistOrderInfoDTO);
        return Result.success();
    }

}
