package com.bogeplus.activity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "优惠券订单关系实体类", description = "当订单模块设置优惠券时需要使用")
public class CouponsOrderDto {

    @ApiModelProperty("优惠券id")
    @NotNull(message = "优惠券不存在")
    private Long id;

    @ApiModelProperty(value = "订单id")
    @NotNull(message = "订单id不存在")
    private Long orderId;

}
