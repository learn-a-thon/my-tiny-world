package com.example.notification.define;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SeverityLevel implements CommonType {
    FATAL("FATAL", "치명적 오류"),
    ERROR("ERROR", "일반적 오류"),
    WARNING("WARNING", "경고"),
    INFO("INFO", "일반적인 정보"),
    DEBUG("DEBUG", "디버그 정보"),
    TRACE("TRACE", "디버그 추적 정보");

    private final String code;
    private final String desc;
}
