package com.bogeplus.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "技师预约返回对象")
public class MassagistAppointmentVO {
    @ApiModelProperty(value = "技师已预约列表")
    private List<Integer> appointments;
}
