package com.example.notification.dto.slack.blocks;

import com.example.notification.define.slack.SlackType;
import com.example.notification.dto.slack.elements.Element;
import lombok.Getter;

import java.util.List;

@Getter
public class ContextBlock  implements Block {
    private SlackType type = SlackType.context;
    private List<Element> elements;
}
