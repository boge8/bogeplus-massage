package com.bogeplus.massage.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户地址坐标响应对象", description = "用户地址坐标响应对象")
public class AddressLocationVO {
    @ApiModelProperty("经纬度")
    private String longitudeLatitude;}
