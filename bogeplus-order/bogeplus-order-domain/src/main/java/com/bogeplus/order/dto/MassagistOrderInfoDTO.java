package com.bogeplus.order.dto;

import com.bogeplus.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: xu
 * @Date: 2024/7/30 - 07 - 30 - 下午4:16
 * @Description: com.bogeplus.order.dto
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MassagistOrderInfoDTO {

    //技师id
    private Long massagistId;

    //订单状态码：1-待支付，2-支付超时，3-待接单，4-已接单，5-技师出发，6-技师到达，7-开始服务，8-服务完成，9-用户评价，10-售后中，11-售后结束，12-订单已关闭，13-订单已取消
    private OrderStatus status;

}
