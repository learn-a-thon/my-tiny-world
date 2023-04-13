package com.twlee.bank.account.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Embeddable
public class AccountDetail {
    @Enumerated(value = EnumType.STRING)
    private TransactionType type;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "amount", column = @Column(name = "transaction_amount"))
    )
    private Cash cash;

    @Column(name = "message")
    private String message;

    protected AccountDetail() {
    }

    public BigDecimal getAmount() {
        return this.cash.getAmount();
    }
}
