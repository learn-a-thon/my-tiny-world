package com.example.notification.dto.slack.blocks;

import com.example.notification.define.slack.SlackType;
import com.example.notification.dto.slack.elements.Element;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ActionBlock implements Block {
    private SlackType type = SlackType.actions;
    @JsonProperty
    private String blockId;
    private List<Element> elements;
}
