package com.bogeplus.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bogeplus.order.dto.MassagistOrderInfoDTO;
import com.bogeplus.order.entity.OrderInfo;
import com.bogeplus.order.entity.OrderInfoExtra;
import com.bogeplus.order.entity.OrderItem;
import com.bogeplus.order.mapper.OrderInfoMapper;
import com.bogeplus.order.mapper.OrderItemMapper;
import com.bogeplus.order.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.order.service.IOrderItemService;
import com.bogeplus.order.vo.MassagistOrderInfoVO;
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

    /**
     * 获取技师订单信息
     * @return
     */
    @Override
    public List<MassagistOrderInfoVO> listbyMassagistId(MassagistOrderInfoDTO massagistOrderInfoDTO) {
        //构造查询条件
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<OrderInfo>()
                .eq("massagist_id",massagistOrderInfoDTO.getMassagistId())
                .eq("status",massagistOrderInfoDTO.getStatus());

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
}
