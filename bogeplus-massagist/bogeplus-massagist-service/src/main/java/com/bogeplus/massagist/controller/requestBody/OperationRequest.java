package com.bogeplus.massagist.controller.requestBody;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "分配操作请求体")
public class OperationRequest {

    @ApiModelProperty(value = "操作类型", required = true)
    private int operation;

    @ApiModelProperty(value = "对象类型", required = true)
    private int type;

    @ApiModelProperty(value = "对象id", required = true)
    private long objId;

    @ApiModelProperty(value = "操作对象id集合", required = true)
    private List<Long> objIdList;
}
