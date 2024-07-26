package com.bogeplus.massage.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserAddressesVO {
    @ApiModelProperty("联系人姓名")
    private String contactName;

    @ApiModelProperty("联系人电话")
    private String contactPhone;

    @ApiModelProperty("服务地址")
    private String address;

    @ApiModelProperty("地址扩展信息")
    private String addressExtra;

    @ApiModelProperty("是否为默认地址")
    private Boolean isDefault;
}
