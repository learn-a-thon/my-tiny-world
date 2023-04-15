package com.twlee.bank.member.application.dto;

public record MemberResponse(
        Long id,
        String name,
        String email,
        String password) {
}
