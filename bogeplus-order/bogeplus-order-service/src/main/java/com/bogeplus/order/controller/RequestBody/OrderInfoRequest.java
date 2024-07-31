package com.bogeplus.order.controller.RequestBody;

import cn.hutool.core.date.DateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "订单信息请求")
public class OrderInfoRequest {
    @ApiModelProperty("项目ID")
    private Long itemId;

    @ApiModelProperty("项目数量")
    private Integer quantity;

    @ApiModelProperty("技师ID")
    private Long massagistId;

    @ApiModelProperty("用户地址ID")
    private Long addressId;

    @ApiModelProperty("服务时间")
    private LocalDateTime serviceTime;

    @ApiModelProperty("优惠卷ID")
    private Long couponId;

    @ApiModelProperty("出行方式")
    private String travelMode;

    @ApiModelProperty("支付方式")
    private String payType;

    @ApiModelProperty("备注信息")
    private String remark;
}
