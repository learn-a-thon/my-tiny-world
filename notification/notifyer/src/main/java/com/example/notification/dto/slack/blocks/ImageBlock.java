package com.example.notification.dto.slack.blocks;

import com.example.notification.define.slack.SlackType;
import com.example.notification.dto.slack.titles.ImageTitle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ImageBlock implements Block {
    private final SlackType type = SlackType.image;
    private ImageTitle title;
    @JsonProperty
    private String blockId;
    @JsonProperty
    private String imageUrl;
    @JsonProperty
    private String altText;
}
