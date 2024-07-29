package com.bogeplus.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.activity.dto.CouponsDto;
import com.bogeplus.activity.entity.CouponsBaseInfo;
import com.bogeplus.activity.mapper.CouponsBaseInfoMapper;
import com.bogeplus.activity.service.CouponsBaseInfoService;
import com.bogeplus.activity.util.RandomCodeGenerator;
import com.bogeplus.common.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券基础信息表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@Service
public class CouponsBaseInfoServiceImpl extends ServiceImpl<CouponsBaseInfoMapper, CouponsBaseInfo> implements CouponsBaseInfoService {

    @Autowired
    private CouponsBaseInfoMapper couponsBaseInfoMapper;

    @Override
    public Result setTime(Long id, Integer expiryDate) {
        // 根据优惠券id设置对应的过期时间
        LambdaUpdateWrapper<CouponsBaseInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(CouponsBaseInfo::getId,id)
                .set(CouponsBaseInfo::getExpiryDate,expiryDate);
        int updated = couponsBaseInfoMapper.update(updateWrapper);
        return updated > 0 ? Result.success("设置过期时间成功") : Result.faild("设置过期时间失败");
    }

    @Override
    public Result addCoupons(CouponsDto couponsDto) {
        // 生成一个随机的四位字符串码,用于分发给其他用户时使用
        String generateUniqueCode = RandomCodeGenerator.generateUniqueCode();
        CouponsBaseInfo couponsBaseInfo = new CouponsBaseInfo();
        BeanUtils.copyProperties(couponsDto,couponsBaseInfo);
        couponsBaseInfo.setCode(generateUniqueCode);
        int ok = couponsBaseInfoMapper.insert(couponsBaseInfo);
        return ok > 0 ? Result.success("优惠券添加成功") : Result.faild("优惠券添加失败");
    }


}
