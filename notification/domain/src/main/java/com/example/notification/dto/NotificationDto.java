package com.example.notification.dto;

import com.example.notification.define.NotificationType;
import com.example.notification.define.SeverityLevel;
import com.example.notification.entity.Notification;
import com.example.notification.util.CommonUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationDto {
    private Long id;
    private NotificationType notificationType;
    private SeverityLevel severityLevel;
    private String message;

    @Builder
    public NotificationDto(Long id, NotificationType notificationType, SeverityLevel severityLevel, String message) {
        this.id = id;
        this.notificationType = notificationType;
        this.severityLevel = severityLevel;
        this.message = message;
    }

    public Notification toEntity() {
        CommonUtil.nonNull(id, notificationType, severityLevel);

        return Notification.builder()
                .id(id)
                .notificationType(notificationType)
                .severityLevel(severityLevel)
                .build();
    }

    public static NotificationDto of(Notification notification) {
        return NotificationDto.builder()
                .id(notification.getId())
                .notificationType(notification.getNotificationType())
                .severityLevel(notification.getSeverityLevel())
                .message(notification.getMessage())
                .build();
    }
}
