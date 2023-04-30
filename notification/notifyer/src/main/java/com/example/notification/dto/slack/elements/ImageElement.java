package com.example.notification.dto.slack.elements;

import com.example.notification.define.slack.SlackType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ImageElement implements Element {
    private SlackType type = SlackType.image;
    @JsonProperty
    private String imageUrl;
    @JsonProperty
    private String altText;
}
