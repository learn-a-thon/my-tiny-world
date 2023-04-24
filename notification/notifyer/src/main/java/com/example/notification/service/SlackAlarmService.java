package com.example.notification.service;

import com.example.notification.config.NotifyProperties;
import com.example.notification.dto.NotifyRequest;
import com.example.notification.dto.NotifyRequestEvent;
import com.example.notification.entity.Notification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SlackAlarmService extends AbstractNotifyService {

    public SlackAlarmService(NotifyProperties notifyProperties, NotificationService notificationService) {
        super(notifyProperties, notificationService);
    }

    @Override
    @Transactional
    public void send(NotifyRequest notifyRequest) {
        notificationService.saveNotification(Notification.defaultOf(notifyRequest.text()));
        this.send(NotifyRequestEvent.of(notifyRequest.text(), notifyProperties.getSlack().getPath()));
    }

}
