package com.bogeplus.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "重复分配的项目名单")
public class CheckItemsVO {
    @ApiModelProperty(value = "技师名称",notes = "噗噗熊")
    String name;
}
