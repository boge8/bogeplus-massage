package com.bogeplus.massagist.service.impl;

import com.bogeplus.common.constant.RedisConstant;
import com.bogeplus.common.util.RedisUtil;
import com.bogeplus.massagist.service.IMassagistAppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MassagistAppointmentServiceImpl implements IMassagistAppointmentService {
    @Override
    public Boolean saveAppointment(long massagistId, LocalDate date, int hour) {

        String massagistAppointmentKey = RedisConstant.format(RedisConstant.MASSAGIST_APPOINTMENT_HOUR, massagistId, date);

        return RedisUtil.zadd(massagistAppointmentKey, hour, hour);
    }
}
