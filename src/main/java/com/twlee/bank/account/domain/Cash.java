package com.twlee.bank.account.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Cash {
    @Column(name = "amount")
    private BigDecimal amount;

    public Cash() {
        this.amount = BigDecimal.ZERO;
    }

    public Cash(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cash cash = (Cash) o;
        return Objects.equals(amount, cash.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
