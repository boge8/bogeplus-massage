package com.bogeplus.message.controller;

import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.Result;
import com.bogeplus.message.controller.request.SmsRequest;
import com.bogeplus.message.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
@Api(value = "短信接口",tags = "短信接口")
public class SmsController {
    @Autowired
    private SmsService smsService;

    @PostMapping("/sendSms")
    @ApiOperation(value = "发送短信")
    public Result sendSms(@RequestBody SmsRequest smsRequest) {
        if(smsRequest.getPhone().equals("18888888888")){
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Boolean bool = smsService.sendSms(smsRequest.getPhone(), smsRequest.getContent());
        return Result.status(bool);
    }
}