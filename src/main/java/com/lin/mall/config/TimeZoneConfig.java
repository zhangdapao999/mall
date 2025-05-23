package com.lin.mall.config;

import java.util.TimeZone;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

// 时区配置
@Component
public class TimeZoneConfig {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
