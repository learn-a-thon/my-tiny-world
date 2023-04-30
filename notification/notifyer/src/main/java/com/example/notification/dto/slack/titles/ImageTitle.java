package com.example.notification.dto.slack.titles;

import com.example.notification.define.slack.SlackType;
import lombok.Getter;

@Getter
public class ImageTitle implements Title {
    private SlackType type = SlackType.image;
    private String text;
}
