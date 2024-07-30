package com.bogeplus.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "取消分配参数")
public class CancelAssignmentDTO {

    @ApiModelProperty(value = "传入对象id", required = true)
    private Long objId;

    @ApiModelProperty(value = "分配对象id", required = true)
    private Long obj2Id;
}
