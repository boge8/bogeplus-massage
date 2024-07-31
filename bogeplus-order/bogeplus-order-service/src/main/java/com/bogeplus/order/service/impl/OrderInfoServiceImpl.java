package com.bogeplus.order.service.impl;

import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.Result;
import com.bogeplus.massage.user.vo.AddressLocationVO;
import com.bogeplus.massagist.feign.MassagistFeign;
import com.bogeplus.massagist.vo.MassagistInfoVO;
import com.bogeplus.order.OrderGaodeAPI;
import com.bogeplus.order.controller.RequestBody.OrderInfoRequest;
import com.bogeplus.order.dto.OrderGaodeDTO;
import com.bogeplus.order.dto.OrderItemDTO;
import com.bogeplus.order.entity.OrderInfo;
import com.bogeplus.order.mapper.OrderInfoMapper;
import com.bogeplus.order.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.order.service.OrderItemService;
import com.bogeplus.user.feign.UserAddressFeign;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private MassagistFeign massagistFeign;

    @Autowired
    private UserAddressFeign userAddressFeign;

    @Override
    public Result saveOrder(OrderInfoRequest request) {
        OrderInfo orderInfo = OrderInfo.nil();
        BeanUtils.copyProperties(request,orderInfo);

        //封装订单项目关系对象
        OrderItemDTO dto = new OrderItemDTO();
        dto.setOrderId(orderInfo.getId());
        dto.setItemId(request.getItemId());
        dto.setQuantity(request.getQuantity());
        //保存关系数据
        if (!orderItemService.saveOrderItem(dto)) {
            Result.faild(ServiceCode.FAILED.getMsg(),ServiceCode.FAILED.getCode());
        }


        OrderGaodeDTO distanceAndCost = getDistanceAndCost(request.getMassagistId(), request.getAddressId());
        orderInfo.setTravelDistance(distanceAndCost.getDistance());
        orderInfo.setTravelCost(distanceAndCost.getCost());
        orderInfo.setTotalPrice(request.getTotalPrice());
        orderInfo.setOutTradeNo("default");
        orderInfo.setPayTime(LocalDateTime.now());
        if (!save(orderInfo)) {
            return Result.faild(ServiceCode.FAILED.getMsg(),ServiceCode.FAILED.getCode());
        }
        return Result.success();
    }

    private OrderGaodeDTO getDistanceAndCost(Long massagistId, Long addressId) {
        Result<MassagistInfoVO> massagistResult = massagistFeign.getById(massagistId);
        checkFeignResult(massagistResult);
        String massagistLocation = massagistResult.getData().getLongtitudeLatitude();
        Result<AddressLocationVO> userResult = userAddressFeign.getAddressLocation(addressId);
        checkFeignResult(userResult);
        String userLocation = userResult.getData().getLongitudeLatitude();
        //稍后完善高德接口
        try {
            return OrderGaodeAPI.getGaodeTaxiDTO(massagistLocation, userLocation);
        } catch (Exception e) {
            throw new BizException("调用高德地图API失败");
        }
    }

    private void checkFeignResult(Result<?> result){
        if (result.getCode() != 200 || result.getData() == null) {
            throw new BizException("调用返回信息异常");
        }
    }
}
