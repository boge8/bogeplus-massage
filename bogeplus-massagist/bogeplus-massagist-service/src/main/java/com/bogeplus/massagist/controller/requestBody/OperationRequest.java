package com.bogeplus.massagist.controller.requestBody;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "分配操作请求体")
public class OperationRequest {

    @ApiModelProperty(value = "操作类型", required = true)
    @NotNull(message = "用户类型不能为空")
    @Max(value = 2,message = "操作类型参数错误")
    @Min(value = 1,message = "操作类型参数错误")
    private int operation;

    @ApiModelProperty(value = "对象类型", required = true)
    @NotNull(message = "用户类型不能为空")
    @Max(value = 2,message = "对象类型参数错误")
    @Min(value = 1,message = "对象类型参数错误")
    private int type;

    @ApiModelProperty(value = "对象id", required = true)
    @NotNull(message = "传入对象不能为空")
    private long objId;

    @ApiModelProperty(value = "操作对象id集合", required = true)
    @NotNull(message = "分配对象集合不能为空")
    private List<Long> objIdList;
}
