package com.twlee.bank.member.application;

import com.twlee.bank.member.application.dto.MemberRequest;
import com.twlee.bank.member.application.dto.MemberResponse;
import com.twlee.bank.member.domain.InMemoryMemberRepository;
import com.twlee.bank.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {
    private MemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        this.memberRepository = new InMemoryMemberRepository();
        this.memberService = new MemberService(memberRepository);
    }

    @Test
    void save(){
        // given
        MemberRequest 홍길동 = new MemberRequest("홍길동", "gildong@gmail.com", "gildong123");

        // when
        MemberResponse member = memberService.save(홍길동);

        // then
        assertThat(member.name()).isEqualTo(홍길동.name());
        assertThat(member.email()).isEqualTo(홍길동.email());
        assertThat(member.password()).isEqualTo(홍길동.password());
    }

    @Test
    void findById(){
        // given
        MemberResponse member = memberService.save(new MemberRequest("홍길동", "gildong@gmail.com", "gildong123"));

        // when
        MemberResponse findMember = memberService.getById(member.id());

        // then
        assertThat(findMember.name()).isEqualTo(member.name());
        assertThat(findMember.email()).isEqualTo(member.email());
        assertThat(findMember.password()).isEqualTo(member.password());
    }
}
