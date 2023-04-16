package com.twlee.bank.account.ui;

import com.twlee.bank.AcceptanceTest;
import com.twlee.bank.member.ui.MemberSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountAcceptanceTest extends AcceptanceTest {

    /**
     * Given 회원가입을 요청한다.
     */
    @BeforeEach
    void setUp() {
        var response = MemberSteps.회원_생성_요청("홍길동", "gildong@gmail.com", "1234");
    }

    /**
     * Given 회원 인증을 하고
     * When 계좌 생성 요청을 하면
     * Then 계좌 생성을 성공한다.
     */
    @Test
    void 인증된_회원은_계좌_생성_요청을_성공한다() {
        String token = "";

        ExtractableResponse<Response> response = AccountSteps.계좌_생성_요청(token);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    /**
     * Given 회원 인증을 하지 않고
     * When 계좌 생성 요청을 하면
     * Then 계좌 생성을 실페한다.
     */
    @Test
    void 미인증_회원은_계좌_생성_요청을_실패한다() {
        String token = "faketoken";

        ExtractableResponse<Response> response = AccountSteps.계좌_생성_요청(token);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    /**
     * Given 회원 인증을 하고
     * When 계좌 정보를 요청 하면
     * Then 계좌 정보를 응답받는다.
     */
    @Test
    void 인증된_회원은_계좌_조회_요청을_성공한다() {
        String token = "";
        ExtractableResponse<Response> saveResponse = AccountSteps.계좌_생성_요청(token);
        String accountNumber = saveResponse.jsonPath().getString("accountNumber");

        ExtractableResponse<Response> response = AccountSteps.계좌_조회_요청(token, accountNumber);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * Given 회원 인증을 하지 않고
     * When 계좌 정보 요청을 하면
     * Then 계좌 정보 조회를 실페한다.
     */
    @Test
    void 미인증_회원은_계좌_조회_요청을_실패한다() {
        String token = "faketoken";
        String accountNumber = "fakeaccountnumber";

        ExtractableResponse<Response> response = AccountSteps.계좌_조회_요청(token, accountNumber);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    /**
     * Given 회원 인증을 하고
     * When 인가되지 않은 계좌 정보를 요청 하면
     * Then 계좌 정보 조회를 실패한다.
     */
    @Test
    void 인가되지_않은_계좌_조회_요청을_실패한다() {
        String token = "";
        ExtractableResponse<Response> saveResponse = AccountSteps.계좌_생성_요청(token);
        String accountNumber = saveResponse.jsonPath().getString("accountNumber");

        ExtractableResponse<Response> response = AccountSteps.계좌_조회_요청(token, accountNumber);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }


    /**
     * Given 회원 인증을 하고
     * When 1000원 입금 요청 하면
     * Then 입금을 성공하고
     * And 계좌 정보를 응답받는다.
     */
    @Test
    void 계좌_입금_요청을_성공한다() {
        String token = "";
        ExtractableResponse<Response> saveResponse = AccountSteps.계좌_생성_요청(token);
        String accountNumber = saveResponse.jsonPath().getString("accountNumber");

        ExtractableResponse<Response> response = AccountSteps.입금_요청(token, accountNumber, BigDecimal.valueOf(1_000));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * Given 회원 인증을 하지 않고
     * When 1000원 입금 요청 하면
     * Then 입금 요청을 실패한다.
     */
    @Test
    void 미인증_회원은_계좌_입금_요청을_실패한다() {
        String token = "faketoken";
        String accountNumber = "fakeaccountnumber";

        ExtractableResponse<Response> response = AccountSteps.입금_요청(token, accountNumber, BigDecimal.valueOf(1_000));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    /**
     * Given 통장에 2000원 보유한 회원이
     * And 회원 인증을 하고
     * When 1000원 출금 요청 하면
     * Then 출금을 성공하고
     * And 계좌 정보를 응답받는다.
     */
    @Test
    void 계좌_출금_요청을_성공한다() {
        String token = "faketoken";
        ExtractableResponse<Response> saveResponse = AccountSteps.계좌_생성_요청(token);
        String accountNumber = saveResponse.jsonPath().getString("accountNumber");
        ExtractableResponse<Response> depositResponse = AccountSteps.입금_요청(token, accountNumber, BigDecimal.valueOf(2_000));

        ExtractableResponse<Response> response = AccountSteps.입금_요청(token, accountNumber, BigDecimal.valueOf(1_000));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }


    /**
     * Given 통장에 500원 보유한 회원이
     * And 회원 인증을 하고
     * When 1000원 출금 요청 하면
     * Then 보유한 잔액이 부족하여 출금을 실패한다.
     */
    @Test
    void 보유_잔액보다_더_많은_금액을_출금_요청하면_실패한다() {
        String token = "";
        ExtractableResponse<Response> saveResponse = AccountSteps.계좌_생성_요청(token);
        String accountNumber = saveResponse.jsonPath().getString("accountNumber");
        ExtractableResponse<Response> depositResponse = AccountSteps.입금_요청(token, accountNumber, BigDecimal.valueOf(500));

        ExtractableResponse<Response> response = AccountSteps.출금_요청(token, accountNumber, BigDecimal.valueOf(1_000));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Given 회원 인증을 하지 않고
     * When 1000원 출금 요청 하면
     * Then 출금 요청을 실패한다.
     */
    @Test
    void 미인증_회원은_계좌_출금_요청을_실패한다() {
        String token = "faketoken";
        ExtractableResponse<Response> saveResponse = AccountSteps.계좌_생성_요청(token);
        String accountNumber = saveResponse.jsonPath().getString("accountNumber");

        ExtractableResponse<Response> response = AccountSteps.출금_요청(token, accountNumber, BigDecimal.valueOf(1_000));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}
