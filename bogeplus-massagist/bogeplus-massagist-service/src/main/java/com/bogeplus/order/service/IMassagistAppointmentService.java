package com.bogeplus.order.service;

import java.time.LocalDate;
import java.util.List;

public interface IMassagistAppointmentService {
    boolean saveAppointment(long massagistId, LocalDate date, int hour);

    List<Integer> getAppointments(long massagistId, LocalDate date);

    boolean removeAppointment(long massagistId, LocalDate date, int hour);
}
