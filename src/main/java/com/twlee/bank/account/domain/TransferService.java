package com.twlee.bank.account.domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferService {

    public void transfer(Account from, Account to, BigDecimal amount) {
        from.withdraw(amount, to.getAccountNumber());
        to.deposit(amount, from.getAccountNumber());
    }
}
