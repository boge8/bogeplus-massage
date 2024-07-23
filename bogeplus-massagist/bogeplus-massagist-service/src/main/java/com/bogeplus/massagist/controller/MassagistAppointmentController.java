package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.controller.request.MassagistAppointmentRequest;
import com.bogeplus.massagist.service.IMassagistAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/massagistAppointment")
public class MassagistAppointmentController {
    @Autowired
    private IMassagistAppointmentService massagistAppointmentService;

    @GetMapping("/saveAppointment")
    public Result saveAppointment(@RequestBody MassagistAppointmentRequest request) {


        Boolean bool = massagistAppointmentService.saveAppointment(request.getMassagistId(),
                request.getAppointmentDate(), request.getAppointmentHour());
        return Result.success(bool);
    }

}
