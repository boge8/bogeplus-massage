package com.bogeplus.order.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.order.entity.OrderEvaluation;
import com.bogeplus.order.service.IOrderEvaluationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户对技师评价表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@RestController
@Api(value = "对技师评价", tags = "对技师评价")
@RequestMapping("/orderEvaluation")
public class OrderEvaluationController {

    @Autowired
    private IOrderEvaluationService iOrderEvaluationService;


    /**
     * 处理对技师的评价请求
     * @param orderEvaluation 评价详情
     * @return 表示操作结果的视图或响应实体
     */
    @PostMapping("/evaluateTechnician")
    public Result evaluateTechnician(@RequestBody OrderEvaluation orderEvaluation) {
        iOrderEvaluationService.evaluateTechnicianByOrderId(orderEvaluation);
        return Result.success();
    }

}