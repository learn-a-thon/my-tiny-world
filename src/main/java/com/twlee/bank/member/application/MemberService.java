package com.twlee.bank.member.application;

import com.twlee.bank.member.application.dto.MemberRequest;
import com.twlee.bank.member.application.dto.MemberResponse;
import com.twlee.bank.member.domain.Member;
import com.twlee.bank.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponse save(MemberRequest memberRequest) {
        Member member = memberRepository.save(memberRequest.toEntity());
        return toResponse(member);
    }

    @Transactional
    public void delete(Long id) {
        Member member = findById(id);
        member.delete();
    }

    public MemberResponse getById(Long id) {
        return toResponse(findById(id));
    }

    private Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 정보입니다."));
    }

    private MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPassword());
    }
}