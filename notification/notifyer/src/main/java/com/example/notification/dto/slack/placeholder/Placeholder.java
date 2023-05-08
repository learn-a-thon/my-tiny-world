package com.example.notification.dto.slack.placeholder;

import com.example.notification.define.slack.SlackType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlainTextPlackHolder.class, name = "plain_text"),
})
public interface Placeholder {
    SlackType getType();
}
