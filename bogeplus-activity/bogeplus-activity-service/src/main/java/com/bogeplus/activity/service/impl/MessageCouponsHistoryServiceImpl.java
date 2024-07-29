package com.bogeplus.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.activity.entity.MessageCouponsHistory;
import com.bogeplus.activity.mapper.MessageCouponsHistoryMapper;
import com.bogeplus.activity.service.MessageCouponsHistoryService;
import com.bogeplus.common.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券历史记录表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@Service
public class MessageCouponsHistoryServiceImpl extends ServiceImpl<MessageCouponsHistoryMapper, MessageCouponsHistory> implements MessageCouponsHistoryService {

    @Resource
    private MessageCouponsHistoryMapper  messageCouponsHistoryMapper;

    @Override
    public Result addHistory(Long id, Long orderId,Long userId) {
        MessageCouponsHistory couponsHistory = new MessageCouponsHistory();
        couponsHistory.setCouponId(id);
        couponsHistory.setUserId(userId);
        couponsHistory.setOrderId(orderId);
        int inserted = messageCouponsHistoryMapper.insert(couponsHistory);
        return inserted > 0 ? Result.success("历史记录添加成功!") : Result.faild("历史记录添加失败");
    }

    @Override
    public Result getHistoryList(Long userId) {
        LambdaQueryWrapper<MessageCouponsHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MessageCouponsHistory::getUserId,userId);
        List<MessageCouponsHistory> messageCouponsHistories = messageCouponsHistoryMapper.selectList(queryWrapper);
        return  Result.success(messageCouponsHistories);
    }
}
