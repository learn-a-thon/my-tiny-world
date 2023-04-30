package com.example.coupon.slack.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "slack")
@Setter
@Getter
public class SlackConfig {
    private String token;
    private String channel;
}
