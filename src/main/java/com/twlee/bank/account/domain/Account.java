package com.twlee.bank.account.domain;

import com.twlee.bank.account.exception.AccountException;
import com.twlee.bank.common.domain.BaseTimeEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Account extends BaseTimeEntity {

    @EmbeddedId
    private AccountNumber accountNumber;

    private Long memberId;

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
        this.cash = new Cash();
    }

    public Account(AccountNumber accountNumber, Long memberId) {
        this.accountNumber = accountNumber;
        this.memberId = memberId;
        this.cash = new Cash();
        this.accountDetails = new ArrayList<>();
    }

    public void deposit(BigDecimal amount) {
        deposit(amount, "");
    }

    public void deposit(BigDecimal amount, String message) {
        Cash depositCash = new Cash(amount);
        this.cash = this.cash.add(depositCash);
        this.accountDetails.add(AccountDetail.createDeposit(amount, message));
    }

    public void withdraw(BigDecimal amount) {
        withdraw(amount, "");
    }

    public void withdraw(BigDecimal amount, String message) {
        Cash withdrawalCash = new Cash(amount);
        if (this.cash.lessThan(withdrawalCash)) {
            throw new AccountException("보유한 잔액이 부족하여 출금을 실패하였습니다.");
        }
        this.cash = this.cash.subtract(withdrawalCash);
        this.accountDetails.add(AccountDetail.createWithdraw(amount, message));
    }

    public void delete() {
        super.delete();
    }

    public String getAccountNumber() {
        return accountNumber.getNumber();
    }

    public Long getMemberId() {
        return memberId;
    }

    public BigDecimal getCash() {
        return cash.getAmount();
    }

    public List<AccountDetail> getAccountDetails() {
        return accountDetails;
    }

    public boolean isOwner(Long memberId) {
        return Objects.equals(this.memberId, memberId);
    }
}
