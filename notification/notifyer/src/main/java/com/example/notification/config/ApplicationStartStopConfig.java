package com.example.notification.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationStartStopConfig implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() {
        System.out.println("Application start");
    }

    @Override
    public void destroy() {
        System.out.println("Application destroy");
    }
}
