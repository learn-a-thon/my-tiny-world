package com.twlee.bank.account.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
public class AccountDetail {
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "amount", column = @Column(name = "transaction_amount"))
    )
    private Cash cash;

    @Column(name = "message")
    private String message;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    protected AccountDetail() {
    }

    private AccountDetail(TransactionType transactionType, Cash cash, String message, LocalDateTime transactionDate) {
        this.transactionType = transactionType;
        this.cash = cash;
        this.message = message;
        this.transactionDate = transactionDate;
    }

    public static AccountDetail createDeposit(BigDecimal transactionAmount, String message) {
        return new AccountDetail(TransactionType.DEPOSIT, new Cash(transactionAmount), message, LocalDateTime.now());
    }

    public static AccountDetail createWithdrawal(BigDecimal transactionAmount, String message) {
        return new AccountDetail(TransactionType.WITHDRAW, new Cash(transactionAmount), message, LocalDateTime.now());
    }

    public BigDecimal getAmount() {
        return this.cash.getAmount();
    }

    @Override
    public String toString() {
        return "AccountDetail{" +
                "transactionType=" + transactionType +
                ", cash=" + cash +
                ", message='" + message + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
