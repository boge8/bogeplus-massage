package com.bogeplus.massagist.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "重复分配的项目名单")
public class CheckMassagistsVO {
    @ApiModelProperty(value = "项目名称",notes = "大保健")
    String name;
}
