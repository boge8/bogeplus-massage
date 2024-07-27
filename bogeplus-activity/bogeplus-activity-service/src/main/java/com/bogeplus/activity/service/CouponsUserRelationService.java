package com.bogeplus.activity.service;

import com.bogeplus.activity.entity.CouponsUserRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.common.util.Result;

import java.util.List;

/**
 * <p>
 * 优惠券和用户的关系表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
public interface CouponsUserRelationService extends IService<CouponsUserRelation> {

    List<CouponsUserRelation> listCoupons(Long aLong);

    Result dispatchForUser(Long id, String userId);
}
