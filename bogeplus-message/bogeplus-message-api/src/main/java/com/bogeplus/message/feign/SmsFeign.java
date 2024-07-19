package com.bogeplus.message.feign;

import com.bogeplus.common.util.Result;
import com.bogeplus.message.dto.SmsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface SmsFeign {
    @PostMapping("/sendSms")
    public Result sendSms(@RequestBody SmsDTO smsDTO) ;
}