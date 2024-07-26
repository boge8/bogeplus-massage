package com.bogeplus.massage.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户地址请求对象", description = "地址表")
public class UserAddressesDTO {
    @ApiModelProperty(value = "联系人姓名", example = "张三")
    private String contactName;

    @ApiModelProperty(value = "联系人电话", example = "13800000000")
    private String contactPhone;

    @ApiModelProperty(value = "服务地址", example = "北京市朝阳区朝阳公园")
    private String address;

    @ApiModelProperty(value = "地址扩展信息", example = "1栋1号")
    private String addressExtra;
}
