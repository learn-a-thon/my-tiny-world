package com.example.notification.dto.slack.placeholder;

import com.example.notification.define.slack.SlackType;
import lombok.Getter;

@Getter
public class PlainTextPlackHolder implements Placeholder {
    private SlackType type;
    private String text;
}
