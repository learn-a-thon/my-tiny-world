package com.twlee.bank.account.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @EmbeddedId
    private AccountNumber accountNumber;

    //TODO
//    @Embedded
//    @AttributeOverrides(
//            @AttributeOverride(name = "id", column = @Column(name = "owner_id"))
//    )
    private Long ownerId;

    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "amount", column = @Column(name = "cash_amount"))
    )
    private Cash cash;

    @ElementCollection
    @CollectionTable(
            name = "account_detail",
            joinColumns = @JoinColumn(name = "account_number"))
    private List<AccountDetail> accountDetails = new ArrayList<>();

    protected Account() {
    }

    public Account(AccountNumber accountNumber, Long ownerId) {
        this.accountNumber = accountNumber;
        this.ownerId = ownerId;
        this.cash = new Cash();
        this.accountDetails = new ArrayList<>();
    }

    public void deposit(BigDecimal amount) {
        Cash depositCash = new Cash(amount);
        this.cash = this.cash.add(depositCash);
    }

    public void withdrawal(BigDecimal amount) {
        Cash withdrawalCash = new Cash(amount);
        if (this.cash.lessThan(withdrawalCash)) {
            throw new IllegalArgumentException("보유한 잔액이 부족하여 출금을 실패하였습니다.");
        }
        this.cash = this.cash.subtract(withdrawalCash);
    }

    public void delete() {
        // TODO
    }

    public String getAccountNumber() {
        return accountNumber.getNumber();
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public BigDecimal getCash() {
        return cash.getAmount();
    }

    public List<AccountDetail> getAccountDetails() {
        return accountDetails;
    }
}
