package com.example.notification.entity;

import com.example.notification.define.NotificationType;
import com.example.notification.define.SeverityLevel;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

    private String message;

    @Builder
    private Notification(Long id, NotificationType notificationType, SeverityLevel severityLevel, String message) {
        this.id = id;
        this.notificationType = notificationType;
        this.severityLevel = severityLevel;
        this.message = message;
    }

    public static Notification defaultOf(String message) {
        return Notification.builder()
                .notificationType(NotificationType.GENERAL)
                .severityLevel(SeverityLevel.INFO)
                .message(message)
                .build();
    }
}
