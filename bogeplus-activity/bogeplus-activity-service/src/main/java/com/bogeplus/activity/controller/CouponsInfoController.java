package com.bogeplus.activity.controller;

import com.bogeplus.activity.dto.CouponsDto;
import com.bogeplus.activity.entity.CouponsInfo;
import com.bogeplus.activity.service.CouponsInfoService;
import com.bogeplus.common.util.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

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

    @ApiOperation(value = "查询优惠券",notes = "查询用户列表下的优惠券")
    @GetMapping("/{userId}")
    public Result listCoupons(@ApiParam(value = "用户id")@PathVariable("userId") Long userId){

        List<CouponsInfo> list = couponsInfoService.listCoupons(userId);
        return Result.success(list);

    }

    @ApiOperation(value = "新增优惠券",notes = "为用户新增优惠券")
    @PostMapping("/addCoupons")
    public Result addCoupons(@ApiParam(value = "商家优惠券参数实体类") @RequestBody CouponsDto couponsDto){

         return couponsInfoService.addCoupons(couponsDto);

    }

    // 优惠券应该不需要手动删除和更新吧......

}
