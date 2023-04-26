package com.twlee.bank.account.ui;

import com.twlee.bank.AcceptanceTest;
import com.twlee.bank.member.ui.AuthenticationSteps;
import com.twlee.bank.member.ui.MemberSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTransferAcceptanceTest extends AcceptanceTest {
    private String 홍길동_계좌번호;
    private String 홍길동_토큰;
    private String 안중근_계좌번호;
    private String 안중근_토큰;

    /**
     * Given 회원 두 명을 생성하고
     * And 계좌 송금을 수행할 계좌 발급을 완료한다.
     */
    @BeforeEach
    void setUp() {
        MemberSteps.회원_생성_요청("홍길동", "from@gmail.com", "1234");
        홍길동_토큰 = AuthenticationSteps.토큰_생성_요청("from@gmail.com", "1234").jsonPath().getString("token");
        var 홍길동_계좌_생성 = AccountSteps.계좌_생성_요청(홍길동_토큰);
        홍길동_계좌번호 = AccountSteps.계좌_조회_요청(홍길동_토큰, 홍길동_계좌_생성).jsonPath().getString("accountNumber");

        MemberSteps.회원_생성_요청("안중근", "to@gmail.com", "5678");
        안중근_토큰 = AuthenticationSteps.토큰_생성_요청("to@gmail.com", "5678").jsonPath().getString("token");
        var 안중근_계좌_생성 = AccountSteps.계좌_생성_요청(안중근_토큰);
        안중근_계좌번호 = AccountSteps.계좌_조회_요청(안중근_토큰, 안중근_계좌_생성).jsonPath().getString("accountNumber");
    }

    /**
     * Given 10,000원을 보유한 홍길동이
     * When 0원을 보유한 안중근에게 6,000원을 송금하면
     * Then 송금을 성공하고
     * And 홍길동은 4,000원 안중근은 6,000원을 보유한다.
     */
    @Test
    void 송금을_성공한다() {
        //given
        AccountSteps.입금_요청(홍길동_토큰, 홍길동_계좌번호, BigDecimal.valueOf(10_000));

        //when
        var response = TransferSteps.송금_요청(홍길동_토큰, 홍길동_계좌번호, 안중근_계좌번호, BigDecimal.valueOf(6_000));

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("accountNumber")).isEqualTo(홍길동_계좌번호);
        assertThat(response.jsonPath().getObject("cash", BigDecimal.class)).isEqualTo(BigDecimal.valueOf(4_000.0));
    }

    /**
     * Given 10,000원을 보유한 홍길동이
     * When 존재하지 않는 계좌에 6,000원을 송금하면
     * Then 송금을 실패한다.
     */
    @Test
    void 존재하지_않는_계좌에_송금하면_실패한다() {
        AccountSteps.입금_요청(홍길동_토큰, 홍길동_계좌번호, BigDecimal.valueOf(10_000));

        var response = TransferSteps.송금_요청(홍길동_토큰, 홍길동_계좌번호, "who-are-u", BigDecimal.valueOf(6_000));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Given 0원을 보유한 홍길동이
     * When 0원을 보유한 안중근에게 6,000원을 송금하면
     * Then 잔액이 부족하여 송금을 실패한다.
     */
    @Test
    void 잔액이_부족하면_송금을_실패한다() {
        var response = TransferSteps.송금_요청(홍길동_토큰, 홍길동_계좌번호, 안중근_계좌번호, BigDecimal.valueOf(6_000));

        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
