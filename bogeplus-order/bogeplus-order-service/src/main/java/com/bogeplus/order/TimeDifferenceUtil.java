package com.bogeplus.order;

import java.time.LocalDateTime;
import java.time.LocalTime;  
import java.time.temporal.ChronoUnit;  
  
public class TimeDifferenceUtil {  
  
    /**  
     * 计算给定时间与当天0时0分0秒之间的差异（毫秒）  
     *   
     * @param dateTime 给定的时间  
     * @return 与当天0时0分0秒之间的差异（毫秒）  
     */  
    public static long calculateMillisecondsFromMidnight(LocalDateTime dateTime) {  
        // 获取给定时间所在日期的0时0分0秒的时间点  
        LocalDateTime midnight = dateTime.toLocalDate().atStartOfDay();  
  
        // 使用ChronoUnit.MILLIS计算两个时间点之间的差异  
        return ChronoUnit.MILLIS.between(midnight, dateTime);  
    }  
  
    public static void main(String[] args) {  
        // 示例：假设当前时间是当天的15时30分15秒  
        LocalDateTime exampleTime = LocalDateTime.of(2025, 10, 1, 01, 30, 00);
  
        // 计算与当天0时0分0秒的差异（毫秒）  
        long milliseconds = calculateMillisecondsFromMidnight(exampleTime);  
  
        // 输出结果  
        System.out.println("与当天0时0分0秒的差异（毫秒）: " + milliseconds);  
    }  
}