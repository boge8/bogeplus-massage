package com.bogeplus.activity.controller;

import com.bogeplus.activity.dto.CouponsDto;
import com.bogeplus.activity.entity.CouponsInfo;
import com.bogeplus.activity.service.CouponsInfoService;
import com.bogeplus.common.util.Result;
import com.bogeplus.common.util.UserUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

import static com.bogeplus.common.enums.ServiceCode.USER_NOT_EXIST;

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

    // 设置优惠券过期时间(生产者设置)


    // 分发给用户优惠券(xx模块进行调用)


    @ApiOperation(value = "新增优惠券",notes = "商户(代指优惠券生产者)新增优惠券")
    @PostMapping("/addCoupons")
    public Result addCoupons(@ApiParam(value = "商家优惠券参数实体类") @RequestBody CouponsDto couponsDto){
         return couponsInfoService.addCoupons(couponsDto);
    }

}
