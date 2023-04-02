package com.example.notification.config;

import com.example.notification.constants.CommonConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "notify")
public class NotifyProperties {
    private Slack slack;

    @Getter
    @Setter
    public static final class Slack {
        private String url;
        private String tokenPath;

        public String getPath() {
            return url + CommonConstants.PATH_DELIMiTER + tokenPath;
        }
    }
}
