package com.bogeplus.massage.user.feign;

import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.Result;
import com.bogeplus.message.dto.SmsDTO;
import com.bogeplus.message.feign.SmsFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SmsFallback implements SmsFeign {
    @Override
    public Result sendSms(SmsDTO smsDTO) {
        log.info("SMS短信发送服务异常:fallback smsDTO:{}", smsDTO);
        throw new BizException(ServiceCode.SMS_SEND_FAILD.getCode(),ServiceCode.SMS_SEND_FAILD.getMsg());
    }
}
