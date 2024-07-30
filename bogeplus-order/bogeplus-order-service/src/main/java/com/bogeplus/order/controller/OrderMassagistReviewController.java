package com.bogeplus.order.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.order.entity.OrderMassagistReview;
import com.bogeplus.order.service.IOrderMassagistReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 技师对客户评价表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@RestController
@Api(value = "技师对客户评", tags = "技师对客户评价")
@RequestMapping("/order/orderMassagistReview")
public class OrderMassagistReviewController {
    @Autowired
    IOrderMassagistReviewService iOrderMassagistReviewService;

    /**
     * 新增 技师对客户评
     * @param orderMassagistReview
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增技师对客户评")
    public Result insertMassagistReview(@RequestBody OrderMassagistReview orderMassagistReview){
        iOrderMassagistReviewService.insertMassagistReview(orderMassagistReview);
        return Result.success();
    }

}
