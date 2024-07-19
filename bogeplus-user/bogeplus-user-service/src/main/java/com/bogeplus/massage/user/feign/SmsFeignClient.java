package com.bogeplus.massage.user.feign;

import com.bogeplus.message.feign.SmsFeign;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        contextId = "smsFeign",
        name = "bogeplus-message",
        fallbackFactory = SmsFallback.class,
        path="/sms")
public interface SmsFeignClient extends SmsFeign {
}
