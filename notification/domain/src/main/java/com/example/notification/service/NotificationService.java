package com.example.notification.service;

import com.example.exception.NotFoundNotificationException;
import com.example.notification.dto.NotificationDto;
import com.example.notification.entity.Notification;
import com.example.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Page<NotificationDto> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable).map(NotificationDto::of);
    }

    public NotificationDto findById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundNotificationException(id));

        return NotificationDto.of(notification);
    }

    public NotificationDto update(Long id, NotificationDto notificationDto) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundNotificationException(id));

        notification.setNotificationType(notificationDto.getNotificationType());
        notification.setSeverityLevel(notificationDto.getSeverityLevel());
        notification.setMessage(notificationDto.getMessage());

        Notification updateNotification = notificationRepository.save(notification);

        return NotificationDto.of(updateNotification);
    }

    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }
}
