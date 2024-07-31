package com.bogeplus.order.service;

import com.bogeplus.common.util.Result;
import com.bogeplus.order.dto.MassagistOrderExtraDTO;
import com.bogeplus.order.dto.MassagistOrderInfoDTO;
import com.bogeplus.order.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.order.vo.MassagistOrderInfoVO;

import java.util.List;

/**
 * <p>
 * 订单信息表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    /**
     * 查询技师订单信息
     * @return
     */
    List<MassagistOrderInfoVO> listbyMassagistId(MassagistOrderInfoDTO massagistOrderInfoDTO);

    /**
     * 技师接单
     *
     * @param orderId
     * @return
     */
    Result confirm(Long orderId);

    /**
     * 技师出发
     *
     * @param orderId
     * @return
     */
    Result depart(Long orderId);

    /**
     * 技师到达
     *
     * @param massagistOrderExtraDTO
     * @return
     */
    Result arrive(MassagistOrderExtraDTO massagistOrderExtraDTO);

    /**
     * 开始服务
     *
     * @param orderId
     * @return
     */
    Result startService(Long orderId);
}
