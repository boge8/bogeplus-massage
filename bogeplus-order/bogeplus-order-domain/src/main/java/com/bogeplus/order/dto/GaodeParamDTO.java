package com.bogeplus.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "高德接口入参对象")
public class GaodeParamDTO {
    @ApiModelProperty("起点经纬度")
    private String origin;

    @ApiModelProperty("终点经纬度")
    private String destination;

    @ApiModelProperty("城市编码")
    private String cityCode;

    @ApiModelProperty("出行方式")
    private String travelMode;
}
