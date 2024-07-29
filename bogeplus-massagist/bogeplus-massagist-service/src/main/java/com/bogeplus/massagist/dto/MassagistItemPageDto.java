package com.bogeplus.massagist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "项目分页实体类", description = "获取项目分页时使用")
public class MassagistItemPageDto {

    @ApiModelProperty(value = "每页记录数", example = "10")
    @NotNull(message = "每页记录数不存在")
    private Integer pageSize;
    @ApiModelProperty(value = "当前页码", example = "1")
    @NotNull(message = "当前页码不存在")
    private Integer pageNo;

}
