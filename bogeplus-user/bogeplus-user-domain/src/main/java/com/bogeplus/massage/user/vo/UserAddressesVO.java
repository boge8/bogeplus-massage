package com.bogeplus.massage.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户地址响应对象", description = "用户地址响应对象")
public class UserAddressesVO {
    @ApiModelProperty("地址id")
    private Long id;

    @ApiModelProperty("联系人姓名")
    private String contactName;

    @ApiModelProperty("联系人电话")
    private String contactPhone;

    @ApiModelProperty("服务地址")
    private String address;

    @ApiModelProperty("地址扩展信息")
    private String addressExtra;

    @ApiModelProperty("经纬度")
    private String longitudeLatitude;

    @ApiModelProperty("是否为默认地址")
    private Boolean isDefault;
}
