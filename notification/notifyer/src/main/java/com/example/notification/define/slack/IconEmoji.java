package com.example.notification.define.slack;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IconEmoji {
    CD(":cd:"),
    GIFT(":gift:"),
    BLUSH(":blush:"),
    SMIRK(":smirk:"),
    SMILEY(":smiley:"),
    RELAXED(":relaxed:"),
    PURPLE_HEART(":purple_heart:"),
    BROKEN_HEART(":broken_heart:"),
    YELLOW_HEART(":yellow_heart:"),
    INNOCENT(":innocent:"),
    BLUE_HEART(":blue_heart:"),
    GREEN_HEART(":green_heart:");

    private final String code;
}
