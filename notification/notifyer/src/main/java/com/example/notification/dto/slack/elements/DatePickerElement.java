package com.example.notification.dto.slack.elements;

import com.example.notification.define.slack.SlackType;
import com.example.notification.dto.slack.texts.Text;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DatePickerElement implements Element {
    private SlackType type = SlackType.datepicker;
    @JsonProperty
    private String actionId;
    @JsonProperty
    private String initialDate;
    private Text placeholder;
}
