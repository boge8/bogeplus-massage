package com.bogeplus.massagist.controller.requestBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "获取列表请求体")
public class GetlistRequest {

    @ApiModelProperty(value = "对象类型",required = true)
    private int type;

    @ApiModelProperty(value = "查询类型",required = true)
    private int status;

    @ApiModelProperty(value = "对象id",required = true)
    private long objId;
}
