package com.bogeplus.activity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "优惠券列表")
public class CouponsVO {
    @ApiModelProperty(value = "满减金额")
    private BigDecimal reduction_price;

    @ApiModelProperty(value = "满减总价")
    private BigDecimal reduction_total_price;

    @ApiModelProperty(value = "有效期截至时间")
    private LocalDateTime expiryDate;

    @ApiModelProperty(value = "优惠券状态")
    private String status;
}
