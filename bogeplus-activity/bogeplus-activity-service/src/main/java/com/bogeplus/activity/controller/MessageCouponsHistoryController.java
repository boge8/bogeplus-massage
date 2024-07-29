package com.bogeplus.activity.controller;

import com.bogeplus.activity.dto.CouponsOrderDto;
import com.bogeplus.activity.service.MessageCouponsHistoryService;
import com.bogeplus.common.util.Result;
import com.bogeplus.common.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.bogeplus.common.enums.ServiceCode.USER_NOT_EXIST;

/**
 * <p>
 * 优惠券历史记录表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@RestController
@Api(value = "优惠券历史记录接口",tags = "优惠券历史记录接口")
@Validated
@RequestMapping("/messageCouponsHistory")
public class MessageCouponsHistoryController {

    @Resource
    private MessageCouponsHistoryService couponsHistoryService;

    @ApiOperation(value = "添加优惠券历史记录",notes = "用户使用优惠券时调用")
    @PostMapping("")
    public Result addHistory(@ApiParam(value = "优惠券订单实体类") @Valid @RequestBody CouponsOrderDto couponsOrderDto){

        Long id = couponsOrderDto.getId();
        Long orderId = couponsOrderDto.getOrderId();
        String userId = UserUtil.getId();
        if (userId == null)
            // 无token进入
            return Result.faild(USER_NOT_EXIST.getMsg(),USER_NOT_EXIST.getCode());
        return couponsHistoryService.addHistory(id,orderId, Long.valueOf(userId));
    }

    @ApiOperation(value = "查询用户优惠券历史记录")
    @GetMapping("")
    public Result getHistoryList(){
        String userId = UserUtil.getId();
        if (userId == null)
            // 无token进入
            return Result.faild(USER_NOT_EXIST.getMsg(),USER_NOT_EXIST.getCode());
        return couponsHistoryService.getHistoryList(Long.valueOf(userId));
    }

}
