package com.bogeplus.activity.service;

import com.bogeplus.activity.dto.CouponsDto;
import com.bogeplus.activity.entity.CouponsInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.common.util.Result;

import java.util.List;

/**
 * <p>
 * 优惠券表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface CouponsInfoService extends IService<CouponsInfo> {

    /**
     * 新增优惠券接口
     * */
    Result addCoupons(CouponsDto couponsDto);

    List<CouponsInfo> listCoupons(Long userId);
}
