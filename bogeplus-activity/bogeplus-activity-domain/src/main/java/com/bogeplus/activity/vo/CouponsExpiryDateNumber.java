package com.bogeplus.activity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "数量与过期时间返回类")
public class CouponsExpiryDateNumber {

    @ApiModelProperty(value = "过期时间")
    private Integer expiryDate;

    @ApiModelProperty(value = "数量")
    private Integer number;

}
