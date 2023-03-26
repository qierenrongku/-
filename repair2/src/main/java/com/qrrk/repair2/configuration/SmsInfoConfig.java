package com.qrrk.repair2.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix ="tx")
public class SmsInfoConfig {
    private Integer appId;
    private String appKey;
    private Integer templateId;
    private String smsSign;
}
