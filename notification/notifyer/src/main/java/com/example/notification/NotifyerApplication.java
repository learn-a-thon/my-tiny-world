package com.example.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NotifyerApplication {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application, domain");
        SpringApplication.run(NotifyerApplication.class, args);
    }
}
