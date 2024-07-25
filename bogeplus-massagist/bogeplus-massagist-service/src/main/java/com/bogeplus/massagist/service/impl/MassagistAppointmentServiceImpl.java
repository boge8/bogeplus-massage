package com.bogeplus.massagist.service.impl;

import com.bogeplus.common.constant.RedisConstant;
import com.bogeplus.common.util.RedisUtil;
import com.bogeplus.massagist.service.IMassagistAppointmentService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MassagistAppointmentServiceImpl implements IMassagistAppointmentService {
    @Override
    public boolean saveAppointment(long massagistId, LocalDate date, int hour) {
        String massagistAppointmentKey = RedisConstant.format(RedisConstant.MASSAGIST_APPOINTMENT_HOUR, massagistId, date);
        boolean isFirstTime = !RedisUtil.exist(massagistAppointmentKey);

        if (!RedisUtil.zadd(massagistAppointmentKey, hour, hour)) {
            return false;
        }
        if (isFirstTime){
            return RedisUtil.expire(massagistAppointmentKey, calculateExpireSeconds(date));
        }

        return true;
    }

    private long calculateExpireSeconds(LocalDate targetDate) {
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();

        // 获取目标日期的次日凌晨0点
        LocalDateTime targetMidnight = targetDate.plusDays(1).atStartOfDay();

        // 计算两个时间点之间的差值
        Duration duration = Duration.between(currentTime, targetMidnight);

        // 获取差值的秒数
        return duration.getSeconds();
    }

    public List<Integer> getAppointments(long massagistId, LocalDate date) {
        String massagistAppointmentKey = RedisConstant.format(RedisConstant.MASSAGIST_APPOINTMENT_HOUR, massagistId, date);
        Set<Serializable> set = RedisUtil.zrange(massagistAppointmentKey, 0, -1);
        if (set != null) {
            return convertSetToList(set);
        }
        return null;
    }

    private List<Integer> convertSetToList(Set<Serializable> set) {
        List<Integer> list = new ArrayList<>();

        for (Serializable item : set) {
            if (item instanceof Integer) {
                list.add((Integer) item);
            } else {
                // Handle potential parsing if needed
                list.add(Integer.parseInt(item.toString()));
            }
        }

        return list;
    }

    @Override
    public boolean removeAppointment(long massagistId, LocalDate date, int hour) {
        return RedisUtil.zrem(RedisConstant.format(RedisConstant.MASSAGIST_APPOINTMENT_HOUR, massagistId, date), hour);
    }
}
