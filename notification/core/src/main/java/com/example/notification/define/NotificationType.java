package com.example.notification.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType implements CommonType {
    GENERAL("GENERAL", "일반 알림");

    private final String code;
    private final String desc;
}
