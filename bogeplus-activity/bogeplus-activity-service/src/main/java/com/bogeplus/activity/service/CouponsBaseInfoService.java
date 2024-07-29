package com.bogeplus.activity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.activity.dto.CouponsDto;
import com.bogeplus.activity.entity.CouponsBaseInfo;
import com.bogeplus.common.util.Result;

/**
 * <p>
 * 优惠券基础信息表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
public interface CouponsBaseInfoService extends IService<CouponsBaseInfo> {

    Result setTime(Long id, Integer expiryDate);

    Result addCoupons(CouponsDto couponsDto);
}
