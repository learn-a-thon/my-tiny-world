package com.twlee.bank.common.application.dto;

public class AnonymousMember extends AuthMember {
    private AnonymousMember() {
    }

    public AnonymousMember(String email) {
        super(email);
    }
}
