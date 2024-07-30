package com.bogeplus.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "技师列表")
public class MassagistVO {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "技师名称")
    private String name;

    @ApiModelProperty(value = "技师头像")
    private String headImg;
}
