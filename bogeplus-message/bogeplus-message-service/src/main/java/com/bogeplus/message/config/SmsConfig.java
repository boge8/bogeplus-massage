package com.bogeplus.message.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sms")
@Component
@Data
public class SmsConfig {
    private String smsSignId;
    private String templateId;
    private String host;
    private String path;
    private String appCode;
}
