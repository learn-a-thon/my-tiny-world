package com.twlee.bank.account.application;

import com.twlee.bank.account.application.dto.AccountResponse;
import com.twlee.bank.account.application.dto.TransactionRequest;
import com.twlee.bank.account.application.dto.TransferRequest;
import com.twlee.bank.member.domain.Member;
import com.twlee.bank.member.domain.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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
    @Autowired
    private AccountQueryService accountQueryService;

    @Test
    void 동시에_출금_수행한다() throws InterruptedException {
        // given
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        String email = "withdraw@bank.com";
        memberRepository.save(new Member("홍길동", email, "1234"));
        String accountNumber = accountService.create(email);
        accountService.deposit(email, new TransactionRequest(accountNumber, BigDecimal.valueOf(10_000)));

        // when
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    accountService.withdraw(email, new TransactionRequest(accountNumber, BigDecimal.valueOf(50)));
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        // then
        AccountResponse accountResponse = accountQueryService.get(email, accountNumber);
        assertEquals(BigDecimal.valueOf(5000), accountResponse.cash());
    }

    /**
     * 100명의 회원이 동시에 홍길동에게 500원을 송금한다.
     */
    @Test
    void 동시에_송금을_수행한다() throws InterruptedException {
        // given
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        String email = "gildong@bank.com";
        memberRepository.save(new Member("홍길동", email, "1234"));
        String accountNumber = accountService.create(email);
        accountService.deposit(email, new TransactionRequest(accountNumber, BigDecimal.valueOf(10_000)));
        Map<String, String> 송금_테스터_목록 = 송금_테스터를_생성(threadCount);

        // when
        for (int i = 1; i <= threadCount; i++) {
            String testerMail = String.format("transfer%d@tinybank.com", i);
            String testerAccountNumber = 송금_테스터_목록.get(testerMail);
            executorService.submit(() -> {
                try {
                    accountService.transfer(testerMail, new TransferRequest(testerAccountNumber, accountNumber, BigDecimal.valueOf(500)));
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        // then
        AccountResponse accountResponse = accountQueryService.get(email, accountNumber);
        assertEquals(BigDecimal.valueOf(60000), accountResponse.cash());
    }

    private Map<String, String> 송금_테스터를_생성(int count) {
        Map<String, String> accountNumbeMap = new HashMap<>();
        for (int i = 1; i <= count; i++) {
            String email = String.format("transfer%d@tinybank.com", i);
            memberRepository.save(new Member("테스터" + i, email, "1234"));
            String accountNumber = accountService.create(email);
            accountNumbeMap.put(email, accountNumber);
            accountService.deposit(email, new TransactionRequest(accountNumber, BigDecimal.valueOf(10_000)));
        }
        return accountNumbeMap;
    }
}
