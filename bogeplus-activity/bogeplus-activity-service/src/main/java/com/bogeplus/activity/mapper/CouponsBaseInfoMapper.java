package com.bogeplus.activity.mapper;

import com.bogeplus.activity.entity.CouponsBaseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bogeplus.activity.vo.CouponsExpiryDateNumber;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

/**
 * <p>
 * 优惠券基础信息表 Mapper 接口
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
public interface CouponsBaseInfoMapper extends BaseMapper<CouponsBaseInfo> {

    CouponsExpiryDateNumber selectByExpiryDateAndNum(Long id);
}
