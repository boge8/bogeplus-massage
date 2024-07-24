package com.bogeplus.activity.controller;

import com.bogeplus.activity.dto.CouponsDto;
import com.bogeplus.activity.entity.CouponsInfo;
import com.bogeplus.activity.service.CouponsInfoService;
import com.bogeplus.common.util.Result;
import com.bogeplus.common.util.UserUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static com.bogeplus.common.enums.ServiceCode.*;

/**
 * <p>
 * 优惠券表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@RestController
@RequestMapping("/couponsInfo")
public class CouponsInfoController {

    @Resource
    private CouponsInfoService couponsInfoService;

    @ApiOperation(value = "查询当前用户的可用优惠券",notes = "查询用户列表下的优惠券")
    @GetMapping("")
    public Result listCoupons(){
        String userId = UserUtil.getId();
        if (userId == null)
            return Result.faild(USER_NOT_EXIST.getMsg(),USER_NOT_EXIST.getCode());
        List<CouponsInfo> list = couponsInfoService.listCoupons(Long.valueOf(userId));
        return Result.success(list);

    }

    @ApiOperation(value = "设置优惠券过期时间",notes = "设置优惠券过期时间(生产者设置)")
    @PutMapping("/{id}/{expiryDate}")
    public Result setTime(@ApiParam(value = "优惠券id")@PathVariable("id")Long id,
                          @ApiParam(value = "过期时间")@PathVariable("expiryDate")LocalDateTime expiryDate){
        if (id == null)
            return Result.faild(COUPONS_NOT_EXIST.getMsg(),COUPONS_NOT_EXIST.getCode());
        if (expiryDate == null)
            return Result.faild(COUPONS_NOT_expiryDate.getMsg(),COUPONS_NOT_expiryDate.getCode());
        return couponsInfoService.setTime(id,expiryDate);
    }

    @ApiOperation(value = "分发给用户优惠券",notes = "分发给用户优惠券(xx模块进行调用)")
    @PutMapping("/{id}/{userId}")
    public Result dispatchForUser(@ApiParam(value = "优惠券id")@PathVariable("id")Long id,
                                  @ApiParam(value = "被分发优惠券的用户id")@PathVariable("userId")Long userId){
        if (id == null)
            return Result.faild(COUPONS_NOT_EXIST.getMsg(),COUPONS_NOT_EXIST.getCode());
        if (userId == null)
            return Result.faild(USER_NOT_EXIST.getMsg(),USER_NOT_EXIST.getCode());
        return couponsInfoService.dispatchForUser(id,userId);
    }


    // 为某个订单选择优惠券
    @ApiOperation(value = "为某个订单选择优惠券",notes = "为某个订单选择优惠券(xx模块进行调用)")
    @PutMapping("/{id}/{orderId}")
    public Result dispatchForOrder(@ApiParam(value = "优惠券id")@PathVariable("id")Long id,
                                  @ApiParam(value = "使用优惠券的订单id")@PathVariable("orderId")Long orderId){
        if (id == null)
            return Result.faild(COUPONS_NOT_EXIST.getMsg(),COUPONS_NOT_EXIST.getCode());
        if (orderId == null)
            return Result.faild(ORDER_NOT_EXIST.getMsg(),ORDER_NOT_EXIST.getCode());
        return couponsInfoService.dispatchForOrder(id,orderId);
    }

    @ApiOperation(value = "新增优惠券",notes = "商户(代指优惠券生产者)新增优惠券")
    @PostMapping("/addCoupons")
    public Result addCoupons(@ApiParam(value = "商家优惠券参数实体类") @RequestBody CouponsDto couponsDto){
         return couponsInfoService.addCoupons(couponsDto);
    }

}
