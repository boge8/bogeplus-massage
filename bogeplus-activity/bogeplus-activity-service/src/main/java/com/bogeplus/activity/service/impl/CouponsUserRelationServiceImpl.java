package com.bogeplus.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.bogeplus.activity.entity.CouponsBaseInfo;
import com.bogeplus.activity.entity.CouponsUserRelation;
import com.bogeplus.activity.mapper.CouponsBaseInfoMapper;
import com.bogeplus.activity.mapper.CouponsUserRelationMapper;
import com.bogeplus.activity.service.CouponsUserRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.activity.vo.CouponsExpiryDateNumber;
import com.bogeplus.activity.vo.CouponsVO;
import com.bogeplus.common.constant.activity.ActivityConstant;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.Result;
import com.bogeplus.common.util.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import static com.bogeplus.common.enums.ServiceCode.COUPONS_NOT_EXIST;

/**
 * <p>
 * 优惠券和用户的关系表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@Service
public class CouponsUserRelationServiceImpl extends ServiceImpl<CouponsUserRelationMapper, CouponsUserRelation> implements CouponsUserRelationService {

    @Resource
    private CouponsUserRelationMapper couponsUserRelationMapper;

    @Resource
    private CouponsBaseInfoMapper couponsBaseInfoMapper;

    @Override
    @Transactional
    public List<CouponsUserRelation> listCoupons(Long userId) {
        // 根据用户id and 过期时间进行列出
        // 当前时间在过期时间前(example: current->   2024-01-01  expiry  ->  2024-07-01)  说明:同下
        // 当前时间在过期时间后(example: current->   2024-08-01  expiry  ->  2024-07-01) 说明:优惠券过期了,因为当前时间超出了它
        LambdaQueryWrapper<CouponsUserRelation> queryWrapper = new LambdaQueryWrapper<>();
        // 获取用户的所有优惠券,区分出过期和非过期即可
        queryWrapper.eq(CouponsUserRelation::getUserId,userId);
        // 执行查询操作
        List<CouponsUserRelation> couponsInfoList = couponsUserRelationMapper.selectList(queryWrapper);

        // 使用流处理优惠券列表，区分过期和未过期的优惠券
        List<CouponsUserRelation> activeCouponsList = couponsInfoList.stream()
                .filter(coupon -> LocalDateTime.now().isBefore(coupon.getExpiryDate())).collect(Collectors.toList());// 筛选出未过期的优惠券

        couponsInfoList.stream()
                .filter(coupon -> LocalDateTime.now().isAfter(coupon.getExpiryDate())) // 筛选出过期的优惠券
                .forEach(expiredCoupon -> {
                    // 遍历过期的优惠券列表并进行删除【后续可优化为消息队列形式,防止数据库插入失败的情况】
                    couponsUserRelationMapper.deleteById(expiredCoupon.getId());
                });
        return activeCouponsList;
    }

    @Override
    @Transactional
    public Result dispatchForUser(Long id, String userId) {
        // 为用户获取优惠券,并附带计算过期时间
        // 先看看优惠券的过期时间,给用户放上去,在今天的基础上持续到对应expiryDate后的十二点时
        CouponsExpiryDateNumber couponsExpiryDateNumber = couponsBaseInfoMapper.selectByExpiryDateAndNum(id);
        Integer expiryDate = couponsExpiryDateNumber.getExpiryDate();
        if (expiryDate == null || couponsExpiryDateNumber.getNumber() <= 0) {
            // 如果没有查询到过期日期 or 优惠券没了，返回错误结果
            return Result.faild(COUPONS_NOT_EXIST.getMsg(),COUPONS_NOT_EXIST.getCode());
        }

        // 获取今天的日期
        LocalDate today = LocalDate.now();
        // 获取当前时间所在时区
        ZoneId systemZoneId = ZoneId.systemDefault();
        // 将expiryDate转换为LocalDate
        LocalDate expiryLocalDate = today.plusDays(expiryDate);
        // 设置过期时间为expiryDate后的午夜12点
        LocalDateTime expiryDateTime = LocalDateTime.of(expiryLocalDate, LocalTime.MIDNIGHT);

        // 根据优惠券ID和用户ID进行优惠券的发放逻辑
        CouponsUserRelation couponsUserRelation = new CouponsUserRelation();
        couponsUserRelation.setCouponId(id);
        couponsUserRelation.setUserId(Long.valueOf(userId));
        couponsUserRelation.setStatus(ActivityConstant.AVAIABLE);
        couponsUserRelation.setReceiveTime(LocalDateTime.now());
        couponsUserRelation.setExpiryDate(expiryDateTime);

        int inserted = couponsUserRelationMapper.insert(couponsUserRelation);
        if (inserted > 0){
            // 成功时修改优惠券数量
            LambdaUpdateWrapper<CouponsBaseInfo> updateWrapper = new LambdaUpdateWrapper<>();
            Integer number = couponsExpiryDateNumber.getNumber();
            number--;
            updateWrapper.eq(CouponsBaseInfo::getId,id)
                    .set(CouponsBaseInfo::getNumber,number);
            couponsBaseInfoMapper.update(updateWrapper);
            return Result.success("优惠券领取成功!");
        }


        return Result.faild("优惠券领取失败,请重试");
    }

    /**
     * 获取当前用户的可用优惠券
     * @return
     */
    public Result<List<CouponsVO>> getAvailableCoupons(){
        List<CouponsUserRelation> relationList = couponsUserRelationMapper.selectList(new LambdaQueryWrapper<CouponsUserRelation>()
                .eq(CouponsUserRelation::getUserId, UserUtil.getId())
                .eq(CouponsUserRelation::getStatus, ActivityConstant.AVAIABLE)
                .eq(CouponsUserRelation::getIsDeleted, ActivityConstant.NOT_DELETED)
                .ge(CouponsUserRelation::getExpiryDate, LocalDateTime.now())  // 过期时间大于当前时间
        );
        if (relationList.isEmpty()) {
            return Result.success();
        }
        List<Long> couponsIds = relationList.stream().map(CouponsUserRelation::getCouponId).collect(Collectors.toList());
        List<CouponsBaseInfo> couponsList = getCouponsByIds(couponsIds);
        return Result.success(getVOList(couponsList,ActivityConstant.AVAIABLE));
    }

    /**
     * 获取当前用户已使用的优惠券
     * @return
     */
    public Result<List<CouponsVO>> getUsedCoupons(){
        List<CouponsUserRelation> relationList = couponsUserRelationMapper.selectList(new LambdaQueryWrapper<CouponsUserRelation>()
                .eq(CouponsUserRelation::getUserId, UserUtil.getId())
                .eq(CouponsUserRelation::getStatus, ActivityConstant.USED)
                .eq(CouponsUserRelation::getIsDeleted, ActivityConstant.NOT_DELETED)
        );
        if (relationList.isEmpty()) {
            return Result.success();
        }
        List<Long> couponsIds = relationList.stream().map(CouponsUserRelation::getCouponId).collect(Collectors.toList());
        List<CouponsBaseInfo> couponsList = getCouponsByIds(couponsIds);
        return Result.success(getVOList(couponsList,ActivityConstant.USED));
    }

    /**
     * 获取当前用户已过期的优惠券
     * @return
     */
    public Result<List<CouponsVO>> getExpiredCoupons(){
        List<CouponsUserRelation> relationList = couponsUserRelationMapper.selectList(new LambdaQueryWrapper<CouponsUserRelation>()
                .eq(CouponsUserRelation::getUserId, UserUtil.getId())
                .eq(CouponsUserRelation::getStatus, ActivityConstant.AVAIABLE)
                .le(CouponsUserRelation::getExpiryDate, LocalDateTime.now())  // 过期时间小于当前时间
        );
        if (relationList.isEmpty()) {
            return Result.success();
        }
        List<Long> couponsIds = relationList.stream().map(CouponsUserRelation::getCouponId).collect(Collectors.toList());
        List<CouponsBaseInfo> couponsList = getCouponsByIds(couponsIds);
        return Result.success(getVOList(couponsList,ActivityConstant.EXPIRED));
    }

    /**
     * 根据传入优惠券id获取优惠券
     * @param ids
     * @return
     */
    private List<CouponsBaseInfo> getCouponsByIds(List<Long> ids){
        return couponsBaseInfoMapper.selectList(new LambdaQueryWrapper<CouponsBaseInfo>()
                .in(CouponsBaseInfo::getId, ids));
    }

    /**
     * 封装响应数据
     * @param couponsList
     * @param status
     * @return
     */
    private List<CouponsVO> getVOList(List<CouponsBaseInfo> couponsList, Integer status) {
        String statusDescription;
        switch (status) {
            case ActivityConstant.AVAIABLE:
                statusDescription = ActivityConstant.VO_AVAIABLE;
                break;
            case ActivityConstant.USED:
                statusDescription = ActivityConstant.VO_USED;
                break;
            case ActivityConstant.EXPIRED:
                statusDescription = ActivityConstant.VO_EXPIRED;
                break;
            default:
                throw new BizException("优惠券状态参数错误");
        }
        return couponsList.stream()
                .map(couponsBaseInfo -> {
                    CouponsVO couponsVO = new CouponsVO();
                    BeanUtils.copyProperties(couponsBaseInfo, couponsVO);
                    couponsVO.setStatus(statusDescription);
                    return couponsVO;
                }).collect(Collectors.toList());
    }

}
