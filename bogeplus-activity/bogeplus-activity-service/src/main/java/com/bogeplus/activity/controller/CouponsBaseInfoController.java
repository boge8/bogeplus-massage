package com.bogeplus.activity.controller;

import com.bogeplus.activity.dto.CouponsDto;
import com.bogeplus.activity.dto.CouponsExpiryDateDto;
import com.bogeplus.activity.entity.CouponsUserRelation;
import com.bogeplus.activity.service.CouponsBaseInfoService;
import com.bogeplus.activity.service.CouponsUserRelationService;
import com.bogeplus.common.util.Result;
import com.bogeplus.common.util.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.bogeplus.common.enums.ServiceCode.USER_NOT_EXIST;

/**
 * <p>
 * 优惠券基础信息表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@RestController
@Validated
@Api(value = "优惠券基础信息接口",tags = "优惠券基础信息接口")
@RequestMapping("/couponsBaseInfo")
public class CouponsBaseInfoController {

    @Resource
    private CouponsBaseInfoService couponsBaseInfoService;

    @ApiOperation(value = "设置优惠券过期时间",notes = "设置优惠券过期时间(生产者设置)")
    @PutMapping("/{id}/{expiryDate}")
    public Result setTime(@ApiParam(value = "优惠券id") @Valid @RequestBody CouponsExpiryDateDto expiryDateDto){
        String userId = UserUtil.getId();
        if (userId == null)
            // 无token进入
            return Result.faild(USER_NOT_EXIST.getMsg(),USER_NOT_EXIST.getCode());
        Long id = expiryDateDto.getId();
        Integer expiryDate = expiryDateDto.getExpiryDate();
        return couponsBaseInfoService.setTime(id,expiryDate);
    }


    @ApiOperation(value = "新增优惠券",notes = "商户(代指优惠券生产者)新增优惠券")
    @PostMapping("/addCoupons")
    public Result addCoupons(@ApiParam(value = "商家优惠券参数实体类") @Valid @RequestBody CouponsDto couponsDto){
        String userId = UserUtil.getId();
        if (userId == null)
            // 无token进入
            return Result.faild(USER_NOT_EXIST.getMsg(),USER_NOT_EXIST.getCode());
        return couponsBaseInfoService.addCoupons(couponsDto);
    }

}
