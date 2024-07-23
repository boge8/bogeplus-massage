package com.bogeplus.massagist.service;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.controller.request.MassagistAppointmentRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface IMassagistAppointmentService {
    public Boolean saveAppointment(long massagistId, LocalDate date, int hour);
}
