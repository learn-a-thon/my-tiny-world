package com.twlee.bank.account.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class Cash {
    @Column(name = "amount")
    private BigDecimal amount;

    public Cash() {
        this.amount = BigDecimal.ZERO;
    }

    public Cash(BigDecimal amount) {
        this.amount = amount;
    }

    public Cash add(Cash cash) {
        return new Cash(this.amount.add(cash.getAmount()));
    }

    public Cash subtract(Cash cash) {
        return new Cash(this.amount.subtract(cash.getAmount()));
    }

    public boolean lessThan(Cash cash) {
        return this.amount.compareTo(cash.getAmount()) < 0;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
