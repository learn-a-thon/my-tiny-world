package com.example.notification.config;


import com.example.notification.constants.CommonConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "notify.slack")
public class SlackConfig {
    private String url;
    private String tokenPath;

    public String getPath() {
        return url + CommonConstants.PATH_DELIMITER + tokenPath;
    }

    public void setTokenPath(String tokenPath) {
        this.tokenPath = tokenPath;
    }

     public void setUrl(String url) {
        this.url = url;
    }
}