package com.example.notification.service;

import com.example.notification.config.NotifyProperties;
import com.example.notification.dto.NotifyRequest;
import com.example.notification.dto.NotifyRequestEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public abstract class AbstractNotifyService implements NotifyService {

    protected final NotifyProperties notifyProperties;
    protected final NotificationService notificationService;

    @Override
    public abstract void send(NotifyRequest notifyRequest);

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void send(NotifyRequestEvent notifyRequestEvent) {
        WebClient.create()
                .post()
                .uri(notifyRequestEvent.getUri())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(notifyRequestEvent), NotifyRequestEvent.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
