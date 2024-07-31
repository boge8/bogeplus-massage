package com.bogeplus.massagist.api;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.dto.MassagistAppointmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bogeplus-massagist")
public interface MassagistAppointmentFeign {
    @PostMapping(value = "/massagistAppointment/getAppointments")
    Result getAppointments(@RequestBody MassagistAppointmentDTO appointmentDTO);
}
