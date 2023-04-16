package com.example.notification.service;

import com.example.notification.config.NotifyProperties;
import com.example.notification.dto.NotifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class SlackAlarmService extends AbstractNotifyService {

    public SlackAlarmService(NotifyProperties notifyProperties) {
        super(notifyProperties);
    }

    @Override
    public void send(NotifyRequest notifyRequest) {
        super.send(notifyRequest);
    }

}
