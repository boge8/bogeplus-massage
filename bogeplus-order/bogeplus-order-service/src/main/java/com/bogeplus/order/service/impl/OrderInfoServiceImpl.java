package com.bogeplus.order.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.Result;
import com.bogeplus.common.util.UserUtil;
import com.bogeplus.massage.user.vo.AddressLocationVO;
import com.bogeplus.massagist.feign.MassagistFeign;
import com.bogeplus.massagist.vo.MassagistInfoVO;
import com.bogeplus.order.api.OrderGaodeAPI;
import com.bogeplus.order.controller.RequestBody.OrderInfoRequest;
import com.bogeplus.order.dto.GaodeParamDTO;
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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Result saveOrder(OrderInfoRequest request) {
        //该写个拦截器
        if (ObjectUtil.isNull(UserUtil.getId())) {
            throw new BizException("登录过期，请重新登录");
        }
        //初始化订单信息
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

        //获取交通距离以及花费
        OrderGaodeDTO distanceAndCost = getDistanceAndCost(request.getMassagistId(), request.getAddressId(), request.getTravelMode());
        orderInfo.setTravelDistance(distanceAndCost.getDistance());
        orderInfo.setTravelCost(distanceAndCost.getCost());

        //封装订单基本信息
        orderInfo.setOrderNum("114514");                           //订单编号
        orderInfo.setUserId(Long.valueOf(UserUtil.getId()));       //用户id
        orderInfo.setStatus((byte) 1);                             //订单状态
        orderInfo.setOutTradeNo("default");                        //外部交易号
        orderInfo.setPayTime(LocalDateTime.now());                 //服务时间
        orderInfo.setTotalPrice(request.getTotalPrice());           //结算金额
        //提交订单
        if (!save(orderInfo)) {
            return Result.faild(ServiceCode.FAILED.getMsg(),ServiceCode.FAILED.getCode());
        }
        return Result.success();
    }

    private OrderGaodeDTO getDistanceAndCost(Long massagistId, Long addressId, String travelMode) {
        Result<MassagistInfoVO> massagistResult = massagistFeign.getById(massagistId);
        checkFeignResult(massagistResult);
        String massagistLocation = massagistResult.getData().getLongtitudeLatitude();
        Result<AddressLocationVO> userResult = userAddressFeign.getAddressLocation(addressId);
        checkFeignResult(userResult);
        String userLocation = userResult.getData().getLongitudeLatitude();
        String userCityCode = userResult.getData().getCityCode();
        //稍后完善高德接口
        try {
            GaodeParamDTO gaodeParamDTO = new GaodeParamDTO();
            gaodeParamDTO.setOrigin(massagistLocation)
                         .setDestination(userLocation)
                         .setCityCode(userCityCode)
                         .setTravelMode(travelMode);
            return OrderGaodeAPI.getGaodeTaxiDTO(gaodeParamDTO);
        } catch (BizException e) {
            throw new BizException(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("调用高德地图API失败");
        }
    }

    private void checkFeignResult(Result<?> result){
        if (result.getCode() != 200 || result.getData() == null) {
            throw new BizException("调用返回信息异常");
        }
    }
}
