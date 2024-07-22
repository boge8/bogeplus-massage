package com.bogeplus.order.service.impl;

import com.bogeplus.order.entity.OrderPayTransaction;
import com.bogeplus.order.mapper.OrderPayTransactionMapper;
import com.bogeplus.order.service.IOrderPayTransactionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付流水表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Service
public class OrderPayTransactionServiceImpl extends ServiceImpl<OrderPayTransactionMapper, OrderPayTransaction> implements IOrderPayTransactionService {

}
