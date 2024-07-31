package com.bogeplus.order.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Auther: xu
 * @Date: 2024/7/31 - 07 - 31 - 下午3:12
 * @Description: com.bogeplus.order.enums
 * @version: 1.0
 */
@Getter
public enum OrderStatus {

    /**
     * 订单状态码：1-待支付，2-支付超时，3-待接单，4-已接单，5-技师出发，6-技师到达，7-开始服务，8-服务完成，9-用户评价，10-售后中，11-售后结束，12-订单已关闭，13-订单已取消
     */
    PENDING_PAYMENT((byte) 1,"待支付"),
    PAY_TIMEOUT((byte) 2,"支付超时"),
    TO_BE_CONFIRMED((byte) 3,"待接单"),
    CONFIRMED((byte) 4,"已接单"),
    DEPARTED((byte) 5,"技师出发"),
    ARRIVED((byte) 6,"技师到达"),
    START_SERVICE((byte) 7,"开始服务"),
    COMPLETED((byte) 8,"服务完成"),
    EVALUATED((byte) 9,"用户评价"),
    AFTER_SALES((byte) 10,"售后中"),
    AFTER_SALES_COMPLETED((byte) 11,"售后结束"),
    CLOSED((byte) 12,"订单已关闭"),
    CANCELLED((byte) 13,"订单已取消");

    @EnumValue
    private final Byte value;
    @JsonValue
    private final String desc;

    OrderStatus(Byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
