package com.bogeplus.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.activity.dto.CouponsDto;
import com.bogeplus.activity.entity.CouponsInfo;
import com.bogeplus.activity.mapper.CouponsInfoMapper;
import com.bogeplus.activity.service.CouponsInfoService;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.util.RedisUtil;
import com.bogeplus.common.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.SerializationUtils.deserialize;
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
        // 根据用户id and 过期时间进行列出,当过期时间大于此时,说明未过期,当此时大于过期时间,说明已过期,列出并删除(后续可在后台中删除,不在此处分发任务)
        LambdaQueryWrapper<CouponsInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CouponsInfo::getUserId,userId).and(
                t -> t.lt(CouponsInfo::getExpiryDate,LocalDateTime.now())
        );
        // 执行查询操作
        List<CouponsInfo> couponsInfoList = couponsInfoMapper.selectList(queryWrapper);

        // 遍历过期的优惠券列表并进行删除(后续可优化为消息队列形式)
        return couponsInfoList;
    }
}
