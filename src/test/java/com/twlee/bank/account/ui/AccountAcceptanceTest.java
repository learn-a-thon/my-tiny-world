package com.twlee.bank.account.ui;

import com.twlee.bank.AcceptanceTest;
import com.twlee.bank.member.ui.MemberSteps;
import org.junit.jupiter.api.BeforeEach;

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

    /**
     * Given 회원 인증을 하지 않고
     * When 계좌 생성 요청을 하면
     * Then 계좌 생성을 실페한다.
     */

    /**
     * Given 회원 인증을 하고
     * When 계좌 정보를 요청 하면
     * Then 계좌 정보를 응답받는다.
     */

    /**
     * Given 회원 인증을 하지 않고
     * When 계좌 정보 요청을 하면
     * Then 계좌 정보 조회를 실페한다.
     */

    /**
     * Given 회원 인증을 하고
     * When 인가되지 않은 계좌 정보를 요청 하면
     * Then 계좌 정보 조회를 실패한다.
     */


}
