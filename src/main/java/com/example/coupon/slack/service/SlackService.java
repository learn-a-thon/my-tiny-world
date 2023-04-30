package com.example.coupon.slack.service;

import com.example.coupon.slack.config.SlackConfig;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class SlackService {
    private final SlackConfig slackConfig;
    public void sendSlackMessage(String message) {

        try {
            MethodsClient methods = Slack.getInstance().methods(slackConfig.getToken());

            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(slackConfig.getChannel())
                    .text(message)
                    .build();
            methods.chatPostMessage(request);
        } catch (SlackApiException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
