package com.bogeplus.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.util.Result;
import com.bogeplus.order.dto.MassagistOrderExtraDTO;
import com.bogeplus.order.dto.MassagistOrderInfoDTO;
import com.bogeplus.order.entity.OrderInfo;
import com.bogeplus.order.enums.OrderStatus;
import com.bogeplus.order.mapper.OrderInfoMapper;
import com.bogeplus.order.service.IOrderInfoExtraService;
import com.bogeplus.order.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.order.service.IOrderItemService;
import com.bogeplus.order.vo.MassagistOrderInfoVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private IOrderItemService orderItemService;
    @Autowired
    private IOrderInfoExtraService orderInfoExtraService;


    /**
     * 查询技师订单信息
     * @return
     */
    @Override
    public List<MassagistOrderInfoVO> listbyMassagistId(MassagistOrderInfoDTO massagistOrderInfoDTO) {
        //构造查询条件
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>()
                .eq("massagist_id",massagistOrderInfoDTO.getMassagistId())
                //根据状态过滤,传入参数为空不参与条件查询
                .eq(ObjectUtils.isNotEmpty(massagistOrderInfoDTO.getStatus()),"status",massagistOrderInfoDTO.getStatus());

        //查询
        List<OrderInfo> orderInfoList = orderInfoMapper.selectList(queryWrapper);

        List<MassagistOrderInfoVO> list = new ArrayList<>();
        for (OrderInfo orderInfo : orderInfoList) {
            //转换为VO对象
            MassagistOrderInfoVO massagistOrderInfoVO = new MassagistOrderInfoVO();
            BeanUtils.copyProperties(orderInfo,massagistOrderInfoVO);
            //查询订单关联项目id
            List<Long> itemIds = orderItemService.listItemIdsByOrderId(orderInfo.getId());
            //存入VO中
            massagistOrderInfoVO.setItemIds(itemIds);
            //添加到返回列表中
            list.add(massagistOrderInfoVO);
        }

        return list;
    }

    /**
     * 技师接单
     *
     * @param orderId
     * @return
     */
    @Override
    public Result confirm(Long orderId) {
        //校验订单状态,只有未接单的订单才能接单
        OrderInfo oldOrderInfo = orderInfoMapper.selectById(orderId);
        if (oldOrderInfo.getStatus() != OrderStatus.TO_BE_CONFIRMED) {
            return Result.faild(ServiceCode.PARAM_ERROR);//订单状态不正确
        }

        //构造修改条件
        OrderInfo orderInfoUpdate = new OrderInfo();
        orderInfoUpdate.setId(orderId);
        orderInfoUpdate.setStatus(OrderStatus.CONFIRMED);

        //修改订单状态
        orderInfoMapper.updateById(orderInfoUpdate);
        return Result.success();
    }

    /**
     * 技师出发
     *
     * @param orderId
     * @return
     */
    @Override
    public Result depart(Long orderId) {
        //校验订单状态,只有已接单的订单才能出发
        OrderInfo oldOrderInfo = orderInfoMapper.selectById(orderId);
        if (oldOrderInfo.getStatus() != OrderStatus.CONFIRMED) {
            return Result.faild(ServiceCode.PARAM_ERROR);//订单状态不正确
        }

        //构造修改条件
        OrderInfo orderInfoUpdate = new OrderInfo();
        orderInfoUpdate.setId(orderId);
        orderInfoUpdate.setStatus(OrderStatus.DEPARTED);

        //修改订单状态
        orderInfoMapper.updateById(orderInfoUpdate);
        return Result.success();
    }

    /**
     * 技师到达
     *
     * @param massagistOrderExtraDTO
     * @return
     */
    @Override
    public Result arrive(MassagistOrderExtraDTO massagistOrderExtraDTO) {
        //校验订单状态,只有已出发的订单才能到达
        OrderInfo oldOrderInfo = orderInfoMapper.selectById(massagistOrderExtraDTO.getId());
        if (oldOrderInfo.getStatus() != OrderStatus.DEPARTED) {
            return Result.faild(ServiceCode.PARAM_ERROR);//订单状态不正确
        }

        //构造修改条件
        OrderInfo orderInfoUpdate = new OrderInfo();
        orderInfoUpdate.setId(massagistOrderExtraDTO.getId());
        orderInfoUpdate.setStatus(OrderStatus.ARRIVED);

        //修改订单表状态
        orderInfoMapper.updateById(orderInfoUpdate);

        //根据订单id新增订单扩展表
        orderInfoExtraService.insertByOrderId(massagistOrderExtraDTO);
        return Result.success();
    }

    /**
     * 开始服务
     *
     * @param orderId
     * @return
     */
    @Override
    public Result startService(Long orderId) {
        //校验订单状态,只有已到达的订单才能开始服务
        OrderInfo oldOrderInfo = orderInfoMapper.selectById(orderId);
        if (oldOrderInfo.getStatus() != OrderStatus.ARRIVED) {
            return Result.faild(ServiceCode.PARAM_ERROR);//订单状态不正确
        }

        //构造修改条件
        OrderInfo orderInfoUpdate = new OrderInfo();
        orderInfoUpdate.setId(orderId);
        orderInfoUpdate.setStatus(OrderStatus.START_SERVICE);

        //修改订单表状态
        orderInfoMapper.updateById(orderInfoUpdate);
        return Result.success();
    }
}
