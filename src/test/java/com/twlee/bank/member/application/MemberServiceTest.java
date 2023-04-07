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
        assertThat(member.getName()).isEqualTo(홍길동.getName());
        assertThat(member.getEmail()).isEqualTo(홍길동.getEmail());
        assertThat(member.getPassword()).isEqualTo(홍길동.getPassword());
    }

    @Test
    void findById(){
        // given
        MemberResponse member = memberService.save(new MemberRequest("홍길동", "gildong@gmail.com", "gildong123"));

        // when
        MemberResponse findMember = memberService.getById(member.getId());

        // then
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
    }
}