package com.bogeplus.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "订单项目关系数据传输对象")
public class OrderItemDTO {
    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("项目ID")
    private Long itemId;

    @ApiModelProperty("项目数量")
    private Integer quantity;
}
