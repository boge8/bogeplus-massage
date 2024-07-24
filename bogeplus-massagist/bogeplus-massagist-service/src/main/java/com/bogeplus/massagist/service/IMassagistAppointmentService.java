package com.bogeplus.massagist.service;

import java.time.LocalDate;
import java.util.List;

public interface IMassagistAppointmentService {
    public Boolean saveAppointment(long massagistId, LocalDate date, int hour);
    public List<Integer> getAppointment(long massagistId, LocalDate date);
}
