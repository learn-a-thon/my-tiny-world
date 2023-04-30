package com.example.notification.dto.slack.blocks;

import com.example.notification.define.slack.SlackType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ImageBlock.class, name = "image"),
        @JsonSubTypes.Type(value = SectionBlock.class, name = "section"),
        @JsonSubTypes.Type(value = ContextBlock.class, name = "context"),
        @JsonSubTypes.Type(value = ActionBlock.class, name = "actions")
})
public interface Block {
    SlackType getType();
}
