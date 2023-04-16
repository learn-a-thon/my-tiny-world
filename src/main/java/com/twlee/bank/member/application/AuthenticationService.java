package com.twlee.bank.member.application;

import com.twlee.bank.common.application.JwtTokenProvider;
import com.twlee.bank.common.exception.AuthenticationException;
import com.twlee.bank.member.application.dto.AuthenticationResponse;
import com.twlee.bank.member.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationService(MemberService memberService, JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AuthenticationResponse authenticate(String email, String password) {
        Member member = memberService.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("존재하지 않는 회원 정보입니다."));
        if (!member.checkPassword(password)) {
            throw new AuthenticationException("유효하지 않은 회원 정보입니다.");
        }
        String token = jwtTokenProvider.createToken(member.getEmail());
        return new AuthenticationResponse(token);
    }
}
