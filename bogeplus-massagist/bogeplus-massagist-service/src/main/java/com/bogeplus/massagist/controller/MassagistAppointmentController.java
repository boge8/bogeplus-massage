package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.controller.request.MassagistAppointmentRequest;
import com.bogeplus.massagist.service.IMassagistAppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/massagistAppointment")
@Api(value = "技师预约接口", tags =  "技师预约接口")
public class MassagistAppointmentController {
    @Autowired
    private IMassagistAppointmentService massagistAppointmentService;

    @PostMapping("/saveAppointment")
    @ApiOperation(value = "保存技师预约")
    public Result saveAppointment(@RequestBody MassagistAppointmentRequest request) {
        if (request.getAppointmentDate().isBefore(LocalDate.now())) {
            return Result.faild("预约日期不能小于当前日期");
        }
        if (request.getAppointmentDate().isAfter(LocalDate.now().plusDays(3))) {
            return Result.faild("预约日期只能是未来三天以内");
        }
        if (request.getAppointmentHour() < 8) {
            return Result.faild("预约时间只能是8~24点");
        }

        Boolean bool = massagistAppointmentService.saveAppointment(request.getMassagistId(),
                request.getAppointmentDate(), request.getAppointmentHour());
        return Result.success(bool);
    }

    @PostMapping("/getAppointment")
    @ApiOperation(value = "获取技师预约")
    public Result getAppointment(@RequestBody MassagistAppointmentRequest request) {
        return Result.success(massagistAppointmentService.getAppointment(request.getMassagistId(),
                request.getAppointmentDate()));
    }

}
