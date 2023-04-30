package com.example.notification.dto.slack.elements;

import com.example.notification.define.slack.SlackType;
import lombok.Getter;

@Getter
public class MarkdownElement implements Element {
    private SlackType type = SlackType.mrkdwn;
    private String text;
}
