package com.bogeplus.massagist.controller.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(value = "预约请求对象")
public class MassagistAppointmentRequest {
    @ApiModelProperty(value = "技师ID",example = "")
    private long massagistId;
    @ApiModelProperty(value = "预约日期",example = "2016-06-12")
    private LocalDate appointmentDate;
    @ApiModelProperty(value = "预约整点",example = "21")
    private int appointmentHour;
}
