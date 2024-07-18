package com.bogeplus.massage.user.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "登录请求参数", description = "登录请求参数")
public class LoginRequest {
    @ApiModelProperty(value = "手机号", example = "18888888888", required = true)
    private String phoneNum;
    @ApiModelProperty(value = "短信验证码", example = "123456", required = true)
    private String smsCode;
//    private String terminalType;   // 0：用户端，1：技师端
}
