package com.example.notification.dto.slack.elements;

import com.example.notification.define.slack.SlackType;
import com.example.notification.dto.slack.option.Option;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OverFlowElement implements Element {
    private SlackType type = SlackType.overflow;
    private List<Option> options;
    @JsonProperty
    private String actionId;
}
