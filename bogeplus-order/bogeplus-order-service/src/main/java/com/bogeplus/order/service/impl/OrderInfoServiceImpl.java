package com.bogeplus.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.bogeplus.common.util.DurationUtil;
import com.bogeplus.order.entity.OrderInfo;
import com.bogeplus.order.mapper.OrderInfoMapper;
import com.bogeplus.order.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    /**
     * 修改订单状态
     *
     * @param orderNum
     * @param status
     */
    @Override
    public boolean updateOrderStatus(String orderNum, Integer status) {
        // 根据订单编号，查询订单
        OrderInfo orderInfo = orderInfoMapper.selectOne(new LambdaUpdateWrapper<OrderInfo>()
                .eq(OrderInfo::getOrderNum, orderNum));
        /**
         * 订单完成：status 改为 8
         * 在修改状态的时候，要校验订单状态、时间是否达到服务的约定时间，
         * 比如：整个服务1小时，你9点到的 最快也是10点结束，不能9:59结束！
         */
        if (status == 8) {
            // 获取当前时间
            LocalDateTime currentTime = LocalDateTime.now();

            // mysql 该字段用 INT 更方便
            // 获取所需服务时间 (存入的是 年-月-日-时:分:秒  需要忽略年月日 仅得出项目多所需时长，此为所需服务时长)
            LocalDateTime serviceTime = orderInfo.getServiceTime();
            // 使用工具类 根据 时：分：秒，获取 所需服务时长，单位毫秒，0点为起点。例:2024-7-31 01:45:00 服务时长为 105分钟
            Long serviceDurationMillis = DurationUtil.getDuration(serviceTime);

            // 查询订单上一次修改时间
            LocalDateTime updateTime = orderInfo.getUpdateTime();

            // 计算以服务时长  (当前时间 - 订单修改时间) ，上一次修改时间 = 服务开始时间
            Duration duration = Duration.between(updateTime, currentTime);
            // 将时间差转换为毫秒
            long millis = duration.toMillis();

            // 所需服务时长 <= 已完成服务时长
            if (serviceDurationMillis <= millis) {
                log.info("服务时间达标");
                // 修改 订单状态 为完成 status = 8
                int i = orderInfoMapper.update(new LambdaUpdateWrapper<OrderInfo>()
                        .eq(OrderInfo::getOrderNum, orderNum)
                        .set(OrderInfo::getStatus, status));
                return i == 1 ? true : false;
            }


        }

        return false;
    }
}
