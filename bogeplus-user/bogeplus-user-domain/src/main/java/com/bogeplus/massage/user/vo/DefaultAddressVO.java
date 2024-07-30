package com.bogeplus.massage.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户默认地址响应对象", description = "用户默认地址响应对象")
public class DefaultAddressVO {
    @ApiModelProperty("联系人姓名")
    private String contactName;

    @ApiModelProperty("联系人电话")
    private String contactPhone;

    @ApiModelProperty("服务地址")
    private String address;
}
