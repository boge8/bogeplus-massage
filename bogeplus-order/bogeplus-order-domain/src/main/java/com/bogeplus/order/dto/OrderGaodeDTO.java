package com.bogeplus.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "订单项目关系数据传输对象")
public class OrderGaodeDTO {
    @ApiModelProperty("交通距离")
    private BigDecimal distance;

    @ApiModelProperty("车费")
    private BigDecimal cost;
}
