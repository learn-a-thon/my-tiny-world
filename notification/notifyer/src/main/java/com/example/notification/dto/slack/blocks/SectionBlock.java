package com.example.notification.dto.slack.blocks;

import com.example.notification.define.slack.SlackType;
import com.example.notification.dto.slack.texts.Text;
import lombok.Getter;

@Getter
public class SectionBlock implements Block {
    private final SlackType type = SlackType.section;
    private Text text;
}
