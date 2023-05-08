package com.example.notification.dto.slack.texts;

import com.example.notification.define.slack.SlackType;
import lombok.Getter;

@Getter
public class Text {
    private SlackType type;
    private String text;
}
