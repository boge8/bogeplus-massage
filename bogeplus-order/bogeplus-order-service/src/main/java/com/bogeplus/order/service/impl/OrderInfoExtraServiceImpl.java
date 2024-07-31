package com.bogeplus.order.service.impl;

import com.bogeplus.order.dto.MassagistOrderExtraDTO;
import com.bogeplus.order.entity.OrderInfoExtra;
import com.bogeplus.order.mapper.OrderInfoExtraMapper;
import com.bogeplus.order.service.IOrderInfoExtraService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单扩展表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Service
public class OrderInfoExtraServiceImpl extends ServiceImpl<OrderInfoExtraMapper, OrderInfoExtra> implements IOrderInfoExtraService {

    /**
     * 根据订单id新增订单扩展表
     * @param massagistOrderExtraDTO
     */
    @Override
    public void insertByOrderId(MassagistOrderExtraDTO massagistOrderExtraDTO) {
        //构造修改条件
        OrderInfoExtra orderInfoExtra = new OrderInfoExtra();
        BeanUtils.copyProperties(massagistOrderExtraDTO,orderInfoExtra);
        //更新
        save(orderInfoExtra);
    }
}
