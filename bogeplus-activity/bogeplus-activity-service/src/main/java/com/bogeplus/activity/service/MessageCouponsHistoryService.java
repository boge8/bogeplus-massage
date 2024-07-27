package com.bogeplus.activity.service;

import com.bogeplus.activity.entity.MessageCouponsHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.common.util.Result;

/**
 * <p>
 * 优惠券历史记录表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
public interface MessageCouponsHistoryService extends IService<MessageCouponsHistory> {

    Result addHistory(Long id, Long orderId,Long userId);

    Result getHistoryList(Long userId);
}
