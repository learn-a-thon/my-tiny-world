package com.example.notification.dto.slack.elements;

import com.example.notification.define.slack.SlackType;
import com.example.notification.dto.slack.texts.Text;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ButtonElement implements Element {
    private final SlackType type = SlackType.button;
    private Text text;
    private String value;
    @JsonProperty
    private String actionId;
}
