package com.example.notification.service;

import com.example.notification.config.NotifyProperties;
import com.example.notification.dto.NotifyRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class AbstractNotifyService implements NotifyService {
    private final NotifyProperties notifyProperties;

    @Override
    public void send(NotifyRequest notifyRequest) {
        WebClient.create()
                .post()
                .uri(notifyProperties.getSlack().getPath())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(notifyRequest), NotifyRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
