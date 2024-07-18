package com.bogeplus.message.service;

public interface SmsService {
    Boolean sendSms(String phone, String content);
}
