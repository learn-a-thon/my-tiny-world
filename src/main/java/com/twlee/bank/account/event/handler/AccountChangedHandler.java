package com.twlee.bank.account.event.handler;

import com.twlee.bank.account.event.AccountChangedEvent;
import com.twlee.bank.common.application.NotificationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccountChangedHandler {
    private final NotificationService consoleNotification;

    public AccountChangedHandler(NotificationService consoleNotification) {
        this.consoleNotification = consoleNotification;
    }

    @EventListener(AccountChangedEvent.class)
    public void handle(AccountChangedEvent event) {
        consoleNotification.send(event.toString());
    }
}
