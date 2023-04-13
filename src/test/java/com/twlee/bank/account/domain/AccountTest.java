package com.twlee.bank.account.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    @Test
    void 계좌를_생성한다() {
        Account account = 계좌_생성(1L);

        assertThat(account.getAccountNumber()).isEqualTo("123456789");
        assertThat(account.getOwnerId()).isEqualTo(1L);
        assertThat(account.getCash()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void 입금한다() {
        Account account = 계좌_생성(1L);

        account.deposit(BigDecimal.valueOf(1_000));

        assertThat(account.getCash()).isEqualTo(BigDecimal.valueOf(1_000));
    }

    @Test
    void 출금한다() {
        Account account = 계좌_생성(1L);
        account.deposit(BigDecimal.valueOf(1_000));

        account.withdrawal(BigDecimal.valueOf(500));

        assertThat(account.getCash()).isEqualTo(BigDecimal.valueOf(500));
    }

    @Test
    void 출금_금액이_통장_잔액보다_크면_출금이_불가하다() {
        Account account = 계좌_생성(1L);

        assertThrows(IllegalArgumentException.class,
                () -> account.withdrawal(BigDecimal.valueOf(1_000)));
    }

    private Account 계좌_생성(Long ownerId) {
        AccountNumber accountNumber = new AccountNumber(() -> "123456789");
        return new Account(accountNumber, ownerId);
    }
}
