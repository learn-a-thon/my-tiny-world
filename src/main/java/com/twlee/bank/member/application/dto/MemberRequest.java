package com.twlee.bank.member.application.dto;

import com.twlee.bank.member.domain.Member;

public record MemberRequest(
        String name,
        String email,
        String password) {

    public Member toEntity() {
        return new Member(name, email, password);
    }
}
