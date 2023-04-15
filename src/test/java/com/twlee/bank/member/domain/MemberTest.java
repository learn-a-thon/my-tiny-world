package com.twlee.bank.member.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    void 회원을_생성한다(){
        Member 홍길동 = new Member(1L, "홍길동", "gildong@gmail.com", "password");

        assertThat(홍길동).isNotNull();
        assertThat(홍길동.getDeletedDate()).isNull();
        assertThat(홍길동.getId()).isEqualTo(1L);
        assertThat(홍길동.getName()).isEqualTo("홍길동");
        assertThat(홍길동.getEmail()).isEqualTo("gildong@gmail.com");
        assertThat(홍길동.getPassword()).isEqualTo("password");
    }

    @Test
    void 회원을_삭제한다(){
        Member 홍길동 = new Member(1L, "홍길동", "gildong@gmail.com", "password");

        홍길동.delete();

        assertThat(홍길동.getDeletedDate()).isNotNull();
    }
}