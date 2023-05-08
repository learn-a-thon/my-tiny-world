package com.example.notification.dto.slack.elements;

import com.example.notification.define.slack.SlackType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ImageElement.class, name = "image"),
        @JsonSubTypes.Type(value = MarkdownElement.class, name = "mrkdwn"),
        @JsonSubTypes.Type(value = DatePickerElement.class, name = "datepicker"),
        @JsonSubTypes.Type(value = OverFlowElement.class, name = "overflow"),
        @JsonSubTypes.Type(value = ButtonElement.class, name = "button"),

})
public interface Element {
    SlackType getType();
}
