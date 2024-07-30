package com.bogeplus.order.service;

import com.bogeplus.order.dto.MassagistOrderExtraDTO;
import com.bogeplus.order.entity.OrderInfoExtra;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单扩展表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface IOrderInfoExtraService extends IService<OrderInfoExtra> {

    /**
     * 根据订单id更新订单扩展表
     * @param massagistOrderExtraDTO
     */
    void updateByOrderId(MassagistOrderExtraDTO massagistOrderExtraDTO);
}
