package com.example.notification.dto.slack.option;

import com.example.notification.dto.slack.texts.Text;
import lombok.Getter;

@Getter
public class Option {
    private Text text;
    private String value;
}
