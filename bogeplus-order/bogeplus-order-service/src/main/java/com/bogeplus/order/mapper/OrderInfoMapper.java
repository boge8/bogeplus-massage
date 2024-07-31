package com.bogeplus.order.mapper;

import com.bogeplus.order.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bogeplus.order.vo.OrderDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    OrderDetailVO getOrderDetail(@Param("orderId") Long orderId);

}
