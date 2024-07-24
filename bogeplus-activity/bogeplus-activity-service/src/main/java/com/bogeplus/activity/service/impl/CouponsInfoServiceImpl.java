package com.bogeplus.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.activity.dto.CouponsDto;
import com.bogeplus.activity.entity.CouponsInfo;
import com.bogeplus.activity.mapper.CouponsInfoMapper;
import com.bogeplus.activity.service.CouponsInfoService;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * <p>
 * 优惠券表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Service
public class CouponsInfoServiceImpl extends ServiceImpl<CouponsInfoMapper, CouponsInfo> implements CouponsInfoService {

    @Resource
    private CouponsInfoMapper couponsInfoMapper;

    @Override
    public Result addCoupons(CouponsDto couponsDto) {
        // 优惠券价格小于0的情况
        if (couponsDto.getReductionTotalPrice().compareTo(BigDecimal.ZERO) < 0 ||
                couponsDto.getReductionPrice().compareTo(BigDecimal.ZERO) < 0){
            return Result.faild(ServiceCode.COUPONS_LESS_THAN_ZERO.getMsg(),ServiceCode.COUPONS_LESS_THAN_ZERO.getCode());
        }

        CouponsInfo couponsInfo = new CouponsInfo();
        copyProperties(couponsDto,couponsInfo);
        int inserted = couponsInfoMapper.insert(couponsInfo);

        return inserted > 0 ? Result.success("优惠券添加成功") : Result.faild("优惠券添加失败");

    }

    @Override
    @Transactional
    public List<CouponsInfo> listCoupons(Long userId) {
        // 根据用户id and 过期时间进行列出
        // 当前时间在过期时间前(example: current->   2024-01-01  expiry  ->  2024-07-01)  说明:同下
        // 当前时间在过期时间后(example: current->   2024-08-01  expiry  ->  2024-07-01) 说明:优惠券过期了,因为当前时间超出了它
        LambdaQueryWrapper<CouponsInfo> queryWrapper = new LambdaQueryWrapper<>();
        // 获取用户的所有优惠券,区分出过期和非过期即可
        queryWrapper.eq(CouponsInfo::getUserId,userId);
        // 执行查询操作
        List<CouponsInfo> couponsInfoList = couponsInfoMapper.selectList(queryWrapper);

        // 使用流处理优惠券列表，区分过期和未过期的优惠券
        List<CouponsInfo> activeCouponsList = couponsInfoList.stream()
                .filter(coupon -> LocalDateTime.now().isBefore(coupon.getExpiryDate())).collect(Collectors.toList());// 筛选出未过期的优惠券

        couponsInfoList.stream()
                .filter(coupon -> LocalDateTime.now().isAfter(coupon.getExpiryDate())) // 筛选出过期的优惠券
                .forEach(expiredCoupon -> {
                    // 遍历过期的优惠券列表并进行删除【后续可优化为消息队列形式,防止数据库插入失败的情况】
                    couponsInfoMapper.deleteById(expiredCoupon.getId());
                });

        return activeCouponsList;
    }

    @Override
    public Result setTime(Long id,LocalDateTime expiryDate) {
        // 针对id为优惠券设置
        LambdaUpdateWrapper<CouponsInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(CouponsInfo::getId,id)
                        .set(CouponsInfo::getExpiryDate,expiryDate);
        int updated = couponsInfoMapper.update(updateWrapper);
        return updated > 0 ? Result.success("设置过期时间成功") : Result.faild("设置过期时间失败");
    }

    @Override
    public Result dispatchForUser(Long id, Long userId) {
        // 针对id为优惠券设置
        LambdaUpdateWrapper<CouponsInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(CouponsInfo::getId,id)
                .set(CouponsInfo::getUserId,userId);
        int updated = couponsInfoMapper.update(updateWrapper);
        return updated > 0 ? Result.success("分发用户成功") : Result.faild("分发用户失败");
    }

    @Override
    public Result dispatchForOrder(Long id, Long orderId) {
        // 针对id为优惠券设置
        LambdaUpdateWrapper<CouponsInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(CouponsInfo::getId,id)
                .set(CouponsInfo::getOrderId,orderId);
        int updated = couponsInfoMapper.update(updateWrapper);
        return updated > 0 ? Result.success("优惠券使用成功") : Result.faild("优惠券使用失败");
    }
}
