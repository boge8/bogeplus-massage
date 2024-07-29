package com.bogeplus.activity.controller;

import com.bogeplus.activity.dto.CouponsOrderDto;
import com.bogeplus.activity.service.CouponsOrderRelationService;
import com.bogeplus.common.util.Result;
import com.bogeplus.common.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.bogeplus.common.enums.ServiceCode.USER_NOT_EXIST;

/**
 * <p>
 * 优惠券和订单的关系表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@RestController
@Validated
@Api(value = "优惠券和订单关系接口",tags = "优惠券和订单关系接口")
@RequestMapping("/couponsOrderRelation")
public class CouponsOrderRelationController {

    @Resource
    private CouponsOrderRelationService couponsOrderRelationService;

    @ApiOperation(value = "订单分配优惠券",notes = "订单分配优惠券(订单模块进行调用)")
    @PostMapping("")
    public Result dispatchForUser(@ApiParam(value = "优惠券订单实体类") @RequestBody  @Valid CouponsOrderDto couponsOrderDto){
        String userId = UserUtil.getId();
        if (userId == null)
            // 无token进入
            return Result.faild(USER_NOT_EXIST.getMsg(),USER_NOT_EXIST.getCode());
        Long id = couponsOrderDto.getId();
        Long orderId = couponsOrderDto.getOrderId();
        return couponsOrderRelationService.dispatchForOrder(id,orderId);
    }

}
