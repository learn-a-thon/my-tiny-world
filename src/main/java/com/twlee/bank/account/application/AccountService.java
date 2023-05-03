package com.twlee.bank.account.application;

import com.twlee.bank.account.application.dto.TransactionRequest;
import com.twlee.bank.account.application.dto.TransferRequest;
import com.twlee.bank.account.application.dto.TransferResponse;
import com.twlee.bank.account.domain.*;
import com.twlee.bank.member.application.MemberService;
import com.twlee.bank.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountQueryService accountQueryService;
    private final AccountNumberCreator accountNumberCreator;
    private final MemberService memberService;
    private final TransferService transferService;

    public AccountService(AccountRepository accountRepository,
                          AccountQueryService accountQueryService,
                          AccountNumberCreator uuidAccountNumberCreator,
                          MemberService memberService,
                          TransferService transferService) {
        this.accountRepository = accountRepository;
        this.accountQueryService = accountQueryService;
        this.accountNumberCreator = uuidAccountNumberCreator;
        this.memberService = memberService;
        this.transferService = transferService;
    }

    @Transactional
    public String create(String memberEmail) {
        Member member = memberService.getMemberByEmail(memberEmail);
        AccountNumber accountNumber = new AccountNumber(accountNumberCreator);
        Account newAccount = new Account(accountNumber, member.getId());
        Account account = accountRepository.save(newAccount);
        return account.getAccountNumber();
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
        Account toAccount = getAccountByAccountNumberWithPessimisticLock(transferRequest.toAccountNumber());
        transferService.transfer(fromAccount, toAccount, transferRequest.amount());
        return new TransferResponse(fromAccount.getAccountNumber(), fromAccount.getCash());
    }

    private Account getMemberAccount(String memberEmail, String accountNumber) {
        return accountQueryService.getMemberAccount(memberEmail, accountNumber);
    }

    private Account getAccountByAccountNumberWithPessimisticLock(String accountNumber) {
        return accountQueryService.getAccountByAccountNumberWithPessimisticLock(accountNumber);
    }
}
