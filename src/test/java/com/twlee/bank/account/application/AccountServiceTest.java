package com.twlee.bank.account.application;

import com.twlee.bank.account.application.dto.AccountResponse;
import com.twlee.bank.account.application.dto.TransactionRequest;
import com.twlee.bank.member.domain.Member;
import com.twlee.bank.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountServiceTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AccountService accountService;

    String EMAIL = "gildong@bank.com";
    String ACCOUNT_NUMBER;

    @BeforeEach
    void setUp() {
        memberRepository.save(new Member("홍길동", EMAIL, "1234"));
        ACCOUNT_NUMBER = accountService.create(EMAIL);
        accountService.deposit(EMAIL, new TransactionRequest(ACCOUNT_NUMBER, BigDecimal.valueOf(10_000)));
    }

    @Test
    void 동시에_출금_수행한다() throws InterruptedException {
        // then
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    run(ACCOUNT_NUMBER, BigDecimal.valueOf(50));
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        // then
        AccountResponse accountResponse = accountService.get(EMAIL, ACCOUNT_NUMBER);
        assertEquals(BigDecimal.valueOf(5000D), accountResponse.cash());
    }

    private void run(String accountNumber, BigDecimal amount) {
        accountService.withdraw(EMAIL, new TransactionRequest(accountNumber, amount));
    }
}
