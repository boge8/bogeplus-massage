package com.bogeplus.order.controller.RequestBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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

    @ApiModelProperty("结算总价")
    private BigDecimal totalPrice;
}
