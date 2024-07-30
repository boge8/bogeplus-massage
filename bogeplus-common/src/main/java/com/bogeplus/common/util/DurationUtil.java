package com.bogeplus.common.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * 根据 LocalDateTime 计算出 服务时长 毫秒数返回
 */
public class DurationUtil {
    public static Long getDuration(LocalDateTime dateTime) {

        // 获取给定时间所在日期的0时0分0秒的时间点
        LocalDateTime midnight = dateTime.toLocalDate().atStartOfDay();

        // 使用ChronoUnit.MILLIS计算两个时间点之间的差异
        return ChronoUnit.MILLIS.between(midnight, dateTime);
    }
}
