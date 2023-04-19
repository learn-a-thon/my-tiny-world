package com.example.notification.dto;

import com.example.notification.define.slack.IconEmoji;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotifyRequestEvent {
    private static final String DEFAULT_USERNAME = "Sota";

    private String text;
    private String uri;
    private String iconEmoji;
    private String username;

    @Builder
    public NotifyRequestEvent(String text, String uri, String iconEmoji, String username) {
        this.text = text;
        this.uri = uri;
        this.iconEmoji = iconEmoji;
        this.username = username;
    }

    public static NotifyRequestEvent of(String text, String uri) {
        return NotifyRequestEvent.builder()
                .text(text)
                .uri(uri)
                .iconEmoji(IconEmoji.PURPLE_HEART.getCode())
                .username(DEFAULT_USERNAME)
                .build();
    }
}
