package com.bogeplus.activity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.activity.entity.CouponsOrderRelation;
import com.bogeplus.activity.mapper.CouponsOrderRelationMapper;
import com.bogeplus.activity.service.CouponsOrderRelationService;
import com.bogeplus.common.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 优惠券和订单的关系表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@Service
public class CouponsOrderRelationServiceImpl extends ServiceImpl<CouponsOrderRelationMapper, CouponsOrderRelation> implements CouponsOrderRelationService {

    @Resource
    private CouponsOrderRelationMapper couponsOrderRelationMapper;

    @Override
    public Result dispatchForOrder(Long id, Long orderId) {
        CouponsOrderRelation orderRelation = new CouponsOrderRelation();
        orderRelation.setCouponId(id);
        orderRelation.setOrderId(orderId);
        int inserted = couponsOrderRelationMapper.insert(orderRelation);
        return inserted > 0 ? Result.success("订单添加优惠券成功!") : Result.faild("订单添加优惠券失败");
    }
}
