package com.example.notification.controller;

import com.example.notification.dto.NotifyRequest;
import com.example.notification.dto.NotifyResponse;
import com.example.notification.service.SlackAlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
public class NotifyController {

    private final SlackAlarmService slackAlarmService;

    @PostMapping("/test")
    public ResponseEntity<NotifyResponse> notifyBasicMessage(@RequestBody NotifyRequest notifyRequest) {
        slackAlarmService.send(notifyRequest);
        return ResponseEntity.ok(NotifyResponse.of(notifyRequest.text()));
    }
}
