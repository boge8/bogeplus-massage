package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.controller.request.MassagistAppointmentRequest;
import com.bogeplus.massagist.service.IMassagistAppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/massagistAppointment")
@Api(value = "技师预约接口", tags =  "技师预约接口")
public class MassagistAppointmentController {
    @Autowired
    private IMassagistAppointmentService massagistAppointmentService;

    @GetMapping("/saveAppointment")
    @ApiOperation(value = "保存技师预约")
    public Result saveAppointment(@RequestBody MassagistAppointmentRequest request) {


        Boolean bool = massagistAppointmentService.saveAppointment(request.getMassagistId(),
                request.getAppointmentDate(), request.getAppointmentHour());
        return Result.success(bool);
    }

    @GetMapping("/getAppointment")
    @ApiOperation(value = "获取技师预约")
    public Result getAppointment(@RequestBody MassagistAppointmentRequest request) {
        return Result.success(massagistAppointmentService.getAppointment(request.getMassagistId(),
                request.getAppointmentDate()));
    }

}
