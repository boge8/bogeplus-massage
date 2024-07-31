package com.bogeplus.order.vo;

import com.bogeplus.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther: xu
 * @Date: 2024/7/30 - 07 - 30 - 下午4:03
 * @Description: com.bogeplus.order.vo
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MassagistOrderInfoVO {

    //订单id
    private Long id;

    //用户id
    private Long userId;

    //订单状态码
    private OrderStatus status;

    //出行费用
    private BigDecimal travelCost;

    //服务时间
    private LocalDateTime serviceTime;

    //技师预计收入
    private BigDecimal expectedIncome;

    //订单关联项目id
    private List<Long> itemIds;

}
