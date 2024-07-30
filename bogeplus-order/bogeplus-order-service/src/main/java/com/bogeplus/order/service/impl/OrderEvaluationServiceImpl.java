package com.bogeplus.order.service.impl;

import com.bogeplus.order.client.MassagistFeigenClient;
import com.bogeplus.order.entity.OrderEvaluation;
import com.bogeplus.order.entity.OrderInfo;
import com.bogeplus.order.mapper.OrderEvaluationMapper;
import com.bogeplus.order.mapper.OrderInfoMapper;
import com.bogeplus.order.service.IOrderEvaluationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 用户对技师评价表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Service
public class OrderEvaluationServiceImpl extends ServiceImpl<OrderEvaluationMapper, OrderEvaluation> implements IOrderEvaluationService {

    @Autowired
    OrderEvaluationMapper orderEvaluationMapper;

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    MassagistFeigenClient massagistFeigenClient;

    /**
     * 处理对技师的评价请求
     * 1.保存到评价表中
     * 2.更新一下技师表中的评分（做一个 总评分/总单数，更新到技师表的评分字段以及更新评论数+1）
     * @param orderEvaluation
     * @return
     */
    @GlobalTransactional // 开启分布式事务
    @Override
    public void evaluateTechnicianByOrderId(OrderEvaluation orderEvaluation) {
        // 新增评价
        orderEvaluationMapper.insert(orderEvaluation);

        // 根据 订单id 查询，技师id
        OrderInfo orderInfo = orderInfoMapper.selectById(orderEvaluation.getOrderId());
        Long massagistId = orderInfo.getMassagistId();

        // 根据 技师 id，查询总评分
        // 查询出技师的全部订单，根据订单查询-->用户评价表(部分订单可能无评价)--> 统计评分 + rating
        BigDecimal sumRating = orderEvaluationMapper.querySumRatingById(massagistId).add(orderEvaluation.getRating());

        // 远程调用技师服务，完成评分、评价数 更新操作
        massagistFeigenClient.updateRatingById(massagistId,sumRating);
    }


}
