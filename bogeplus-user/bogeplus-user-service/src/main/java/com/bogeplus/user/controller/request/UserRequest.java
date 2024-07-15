package com.bogeplus.user.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户注册请求参数",description = "用户注册请求参数")
public class UserRequest {
    @ApiModelProperty(value = "用户名",example = "李博",required = true)
    private String username;
    @ApiModelProperty(value = "密码",example = "123123",required = true)
    private String password;
    @ApiModelProperty(value = "手机号",example = "123123",required = true)
    private String phone;
    @ApiModelProperty(value = "邮箱",example = "123123",required = true)
    private String email;
    @ApiModelProperty(value = "地址",example = "123123",required = true)
    private String address;
}
