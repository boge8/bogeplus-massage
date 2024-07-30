package com.bogeplus.activity.controller;

import com.bogeplus.activity.entity.CouponsUserRelation;
import com.bogeplus.activity.service.CouponsUserRelationService;
import com.bogeplus.activity.vo.CouponsVO;
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
 * 优惠券和用户的关系表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@RestController
@Validated
@Api(value = "优惠券和用户接口",tags = "优惠券和用户接口")
@RequestMapping("/couponsUserRelation")
public class CouponsUserRelationController {

    @Resource
    private CouponsUserRelationService couponsUserRelationService;

    @ApiOperation(value = "用户领取优惠券",notes = "用户领取优惠券(用户模块进行调用)")
    @PostMapping("/{id}")
    public Result dispatchForUser(@ApiParam(value = "优惠券id") @Valid @PathVariable("id")Long id){
        String userId = UserUtil.getId();
        if (userId == null)
            // 无token进入
            return Result.faild(USER_NOT_EXIST.getMsg(),USER_NOT_EXIST.getCode());
        return couponsUserRelationService.dispatchForUser(id,userId);
    }

    @ApiOperation(value = "查询当前用户的可用优惠券",notes = "查询用户列表下的优惠券(用户模块进行调用)")
    @GetMapping("")
    public Result listCoupons(){
        String userId = UserUtil.getId();
        if (userId == null)
            // 无token进入
            return Result.faild(USER_NOT_EXIST.getMsg(),USER_NOT_EXIST.getCode());
        List<CouponsUserRelation> list = couponsUserRelationService.listCoupons(Long.valueOf(userId));
        return Result.success(list);
    }

    @ApiOperation(value = "用户端查询当前用户所有可用优惠券",notes = "用户端查询当前用户所有可用优惠券")
    @GetMapping("/getAvailable")
    public Result<List<CouponsVO>> getAvailable(){
        return couponsUserRelationService.getAvailableCoupons();
    }

    @ApiOperation(value = "用户端查询当前用户所有已使用优惠券",notes = "用户端查询当前用户所有已使用优惠券")
    @GetMapping("/getUsed")
    public Result<List<CouponsVO>> getUsed(){
        return couponsUserRelationService.getUsedCoupons();

    }

    @ApiOperation(value = "用户端查询当前用户所有已使用优惠券",notes = "用户端查询当前用户所有已使用优惠券")
    @GetMapping("/getExpired")
    public Result<List<CouponsVO>> getExpired(){
        return couponsUserRelationService.getExpiredCoupons();
    }


}
