package com.bogeplus.activity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@ApiModel(value = "优惠券过期时间设置实体类", description = "用于调用设置优惠券过期时间中使用")
@NoArgsConstructor
public class CouponsExpiryDateDto {

    @ApiModelProperty("优惠券id")
    @NotNull(message = "优惠券不存在")
    private Long id;

    @ApiModelProperty(value = "过期时间",notes = "具体指天数,几天过期")
    @NotNull(message = "过期时间不存在")
    Integer expiryDate;

}
