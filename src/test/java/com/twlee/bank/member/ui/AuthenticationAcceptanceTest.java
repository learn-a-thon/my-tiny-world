package com.twlee.bank.member.ui;

import com.twlee.bank.AcceptanceTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.twlee.bank.member.ui.AuthenticationSteps.토큰_생성_요청;
import static com.twlee.bank.member.ui.MemberSteps.회원_생성_요청;
import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationAcceptanceTest extends AcceptanceTest {
    public static String NAME = "홍길동";
    public static String EMAIL = "gildong@gmail.com";
    public static String PASSWORD = "gildong123";

    /**
     * Given 회원 가입을 성공하고
     * When 토큰 생성 요청을 하면
     * Then 생성된 토큰을 응답 받는다.
     */
    @Test
    void 토근을_생성한다() {
        // given
        var saveResponse = 회원_생성_요청(NAME, EMAIL, PASSWORD);
        // when
        var tokenResponse = 토큰_생성_요청(EMAIL, PASSWORD);
        // then
        assertThat(tokenResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(tokenResponse.jsonPath().getString("token")).isNotNull();
    }

    /**
     * When 존재하지 않거나 유효하지 않은 회원이 토큰 생성 요청을 하면
     * Then 토큰 생성을 실패한다.
     */
    @Test
    void 존재하지_않거나_유효하지_않은_회원은_토큰_생성을_실패한다() {
        // given
        // when
        var tokenResponse = 토큰_생성_요청("whysoserious@anonymous.com", "kkk");
        // then
        assertThat(tokenResponse.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}
