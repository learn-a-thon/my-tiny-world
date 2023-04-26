package com.twlee.bank.account.application;

import com.twlee.bank.account.application.dto.AccountResponse;
import com.twlee.bank.account.application.dto.TransactionRequest;
import com.twlee.bank.account.application.dto.TransferRequest;
import com.twlee.bank.account.application.dto.TransferResponse;
import com.twlee.bank.account.domain.*;
import com.twlee.bank.account.exception.AccountException;
import com.twlee.bank.member.application.MemberService;
import com.twlee.bank.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountNumberCreator accountNumberCreator;
    private final MemberService memberService;
    private final TransferService transferService;

    public AccountService(AccountRepository accountRepository,
                          AccountNumberCreator uuidAccountNumberCreator,
                          MemberService memberService,
                          TransferService transferService) {
        this.accountRepository = accountRepository;
        this.accountNumberCreator = uuidAccountNumberCreator;
        this.memberService = memberService;
        this.transferService = transferService;
    }

    @Transactional
    public String create(String memberEmail) {
        Member member = getMemberByEmail(memberEmail);
        AccountNumber accountNumber = new AccountNumber(accountNumberCreator);
        Account newAccount = new Account(accountNumber, member.getId());
        Account account = accountRepository.save(newAccount);
        return account.getAccountNumber();
    }

    public AccountResponse get(String memberEmail, String accountNumber) {
        Member member = getMemberByEmail(memberEmail);
        Account account = getMemberAccount(memberEmail, accountNumber);
        return new AccountResponse(member.getName(), member.getEmail(), account.getAccountNumber());
    }

    @Transactional
    public void deposit(String memberEmail, TransactionRequest transactionRequest) {
        Account account = getMemberAccount(memberEmail, transactionRequest.accountNumber());
        account.deposit(transactionRequest.amount());
    }

    @Transactional
    public void withdraw(String memberEmail, TransactionRequest transactionRequest) {
        Account account = getMemberAccount(memberEmail, transactionRequest.accountNumber());
        account.withdraw(transactionRequest.amount());
    }

    @Transactional
    public void delete(String memberEmail, String accountNumber) {
        Account account = getMemberAccount(memberEmail, accountNumber);
        account.delete();
    }

    @Transactional
    public TransferResponse transfer(String memberEmail, TransferRequest transferRequest) {
        Account fromAccount = getMemberAccount(memberEmail, transferRequest.fromAccountNumber());
        Account toAccount = getAccountByAccountNumber(transferRequest.toAccountNumber());
        transferService.transfer(fromAccount, toAccount, transferRequest.amount());
        return new TransferResponse(fromAccount.getAccountNumber(), fromAccount.getCash());
    }

    private Account getMemberAccount(String memberEmail, String accountNumber) {
        Member member = getMemberByEmail(memberEmail);
        Account account = getAccountByAccountNumber(accountNumber);
        if (!account.isOwner(member.getId())) {
            throw new AccountException("잘못된 접근입니다.");
        }
        return account;
    }

    private Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(new AccountNumber(accountNumber))
                .orElseThrow(() -> new AccountException("존재하지 않는 계좌 정보입니다."));
    }

    private Member getMemberByEmail(String memberEmail) {
        return memberService.findByEmail(memberEmail)
                .orElseThrow(() -> new AccountException("존재하지 않는 회원 정보입니다."));
    }
}
