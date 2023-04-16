package com.twlee.bank.account.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountNumberTest {

    @Test
    void 계좌번호를_생성한다() {
        AccountNumber accountNumber = new AccountNumber(() -> "123456789");

        assertEquals("123456789", accountNumber.getNumber());
    }
}
