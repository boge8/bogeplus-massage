package com.bogeplus.common.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页结果", description = "分页结果")
public class PageResult<T> extends Result<T>{
    @ApiModelProperty(value = "总记录数", example = "100")
    private Integer total;
    @ApiModelProperty(value = "每页记录数", example = "10")
    private Integer pageSize;
    @ApiModelProperty(value = "当前页码", example = "1")
    private Integer pageNo;

}
