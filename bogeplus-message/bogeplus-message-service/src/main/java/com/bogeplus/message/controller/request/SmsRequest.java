package com.bogeplus.message.controller.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "短信请求对象")
public class SmsRequest {
    @ApiModelProperty(value = "手机号",example = "13245678901")
    private String phone;
    @ApiModelProperty(value = "短信内容",example = "验证码：123412")
    private String content;

}
