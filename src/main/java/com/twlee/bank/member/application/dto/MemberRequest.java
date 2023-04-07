package com.twlee.bank.member.application.dto;

import com.twlee.bank.member.domain.Member;

public final class MemberRequest {
    private String name;
    private String email;
    private String password;

    private MemberRequest() {
    }

    public MemberRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Member toEntity() {
        return new Member(name, email, password);
    }
}
