package com.example.notification.define.slack;

import com.example.notification.define.CommonType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SlackType implements CommonType<String, String> {
    context("context", "context"),
    header("header", "header"),
    section("section", "section"),
    plain_text("plain_text", "plain_text"),
    mrkdwn("mrkdwn", "mrkdwn"),
    image("image", "image"),
    actions("actions", "actions"),
    datepicker("datepicker", "datepicker"),
    overflow("overflow", "overflow"),
    button("button", "button");

    private final String code;
    private final String desc;

}