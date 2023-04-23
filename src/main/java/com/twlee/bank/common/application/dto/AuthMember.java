package com.twlee.bank.common.application.dto;

public class AuthMember {
    private String email;

    protected AuthMember() {
    }

    public AuthMember(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
