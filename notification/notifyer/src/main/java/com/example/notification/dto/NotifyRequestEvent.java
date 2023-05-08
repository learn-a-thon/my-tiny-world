package com.example.notification.dto;

import com.example.notification.dto.slack.blocks.Block;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotifyRequestEvent {
    private static final String DEFAULT_USERNAME = "Sota";

    private String text;
    private String uri;
    private String iconEmoji;
    private String username;

    private List<Block> blocks;

    @Builder
    public NotifyRequestEvent(String text, String uri, String iconEmoji, String username, List<Block> blocks) {
        this.text = text;
        this.uri = uri;
        this.iconEmoji = iconEmoji;
        this.username = username;
        this.blocks = blocks;
    }

    public static NotifyRequestEvent of(NotifyRequest notifyRequest, String uri) {
        return NotifyRequestEvent.builder()
                .text(notifyRequest.text())
                .blocks(notifyRequest.blocks())
                .uri(uri)
                .username(DEFAULT_USERNAME)
                .build();
    }
}
