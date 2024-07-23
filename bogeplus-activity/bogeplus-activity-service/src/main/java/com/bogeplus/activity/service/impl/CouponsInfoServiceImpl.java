package com.bogeplus.activity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.activity.dto.CouponsDto;
import com.bogeplus.activity.entity.CouponsInfo;
import com.bogeplus.activity.mapper.CouponsInfoMapper;
import com.bogeplus.activity.service.CouponsInfoService;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.util.RedisUtil;
import com.bogeplus.common.util.Result;
import org.springframework.stereotype.Service;

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

    private final String COUPONS_LIST = "COUPONS_LIST";

    @Override
    public Result addCoupons(CouponsDto couponsDto) {
        // 优惠券价格小于0的情况
        if (couponsDto.getReductionTotalPrice().compareTo(BigDecimal.ZERO) < 0 ||
                couponsDto.getReductionPrice().compareTo(BigDecimal.ZERO) < 0){
            return Result.faild(ServiceCode.COUPONS_LESS_THAN_ZERO.getMsg(),ServiceCode.COUPONS_LESS_THAN_ZERO.getCode());
        }

        // 过期时间小于今天的情况
        LocalDateTime now = LocalDateTime.now();
        if (couponsDto.getExpiryDate().isBefore(now)){
            return Result.faild(ServiceCode.COUPONS_LESS_THAN_TODAY.getMsg(),ServiceCode.COUPONS_LESS_THAN_TODAY.getCode());
        }
        // 过期时间需要减去今天开始计算剩余可用的天数
        Period between = Period.between(now.toLocalDate(), couponsDto.getExpiryDate().toLocalDate());
        // 获得天数
        int days = between.getDays();


        CouponsInfo couponsInfo = new CouponsInfo();
        copyProperties(couponsDto,couponsInfo);
        // 拷贝后,添加对应优惠券
        // 并在redis留存一份具体的时间,当用户使用或到时过期再删除
        // 设计思路:新建后,将优惠券作为列表存储到redis
        // 当用户使用时,删除redis中的数据;当过期时,当用户查询时优惠券时,再从redis查询对应的优惠券即可
        List<Serializable> list = new ArrayList<>();
        list.add(couponsInfo);
        boolean isOk = RedisUtil.setListWithIndividualExpire(COUPONS_LIST + couponsDto.getUserId(), list,days);
        if (isOk)
            return Result.success("优惠券添加成功");
        return Result.faild("优惠券添加失败");
    }

    @Override
    public List<CouponsInfo> listCoupons(Long userId) {
        List<Serializable> list = RedisUtil.getList(COUPONS_LIST + userId);
        List<CouponsInfo> couponsInfoList = new ArrayList<>();
        for (Serializable serializedObj : list) {
            try {
                // 将字符串反序列化为CouponsInfo对象
                CouponsInfo obj = deserialize((byte[]) serializedObj);
                couponsInfoList.add(obj);
            } catch (Exception e) {
                e.printStackTrace();
                // 处理异常，例如记录日志或返回空列表
            }
        }
        return couponsInfoList;
    }
}
