package com.twlee.bank.common.infra;

import com.twlee.bank.common.application.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsoleNotification implements NotificationService {
    @Override
    public void send(String message) {
        log.info("[Notification] {}", message);
    }
}
