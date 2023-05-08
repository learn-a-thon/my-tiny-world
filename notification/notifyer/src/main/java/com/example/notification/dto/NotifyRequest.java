package com.example.notification.dto;

import com.example.notification.dto.slack.blocks.Block;

import java.util.List;

public record NotifyRequest(String text, List<Block> blocks) {}
