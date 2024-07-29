package com.bogeplus.massagist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(value = "预约请求对象")
public class MassagistAppointmentDTO {
    @ApiModelProperty(value = "技师ID",example = "", required = true)
    private long massagistId;
    @ApiModelProperty(value = "预约日期",example = "2024-07-27", required = true)
    private LocalDate appointmentDate;
    @ApiModelProperty(value = "预约整点",example = "21", required = true)
    private int appointmentHour;
}
