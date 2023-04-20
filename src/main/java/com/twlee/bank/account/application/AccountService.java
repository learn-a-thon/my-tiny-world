package com.twlee.bank.account.application;

import com.twlee.bank.account.application.dto.TransactionRequest;
import com.twlee.bank.account.domain.Account;
import com.twlee.bank.account.domain.AccountNumber;
import com.twlee.bank.account.domain.AccountNumberCreator;
import com.twlee.bank.account.domain.AccountRepository;
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

    public AccountService(AccountRepository accountRepository,
                          AccountNumberCreator uuidAccountNumberCreator,
                          MemberService memberService) {
        this.accountRepository = accountRepository;
        this.accountNumberCreator = uuidAccountNumberCreator;
        this.memberService = memberService;
    }

    @Transactional
    public String create(String memberEmail) {
        Member member = getMemberByEmail(memberEmail);

        AccountNumber accountNumber = new AccountNumber(accountNumberCreator);
        Account newAccount = new Account(accountNumber, member.getId());
        Account account = accountRepository.save(newAccount);
        return account.getAccountNumber();
    }

    @Transactional
    public void deposit(String memberEmail, TransactionRequest transactionRequest) {
        Member member = getMemberByEmail(memberEmail);

        Account account = getAccount(member.getId(), transactionRequest.accountNumber());

        account.deposit(transactionRequest.amount());
    }

    @Transactional
    public void withdraw(String memberEmail, TransactionRequest transactionRequest) {
        Member member = getMemberByEmail(memberEmail);

        Account account = getAccount(member.getId(), transactionRequest.accountNumber());

        account.withdraw(transactionRequest.amount());
    }

    @Transactional
    public void delete(String memberEmail, String accountNumber) {
        Member member = getMemberByEmail(memberEmail);
        Account account = getAccount(member.getId(), accountNumber);
        account.delete();
    }

    private Account getAccount(Long memberId, String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountException("존재하지 않는 계좌 정보입니다."));
        if (!account.isOwner(memberId)) {
            throw new AccountException("잘못된 접근입니다.");
        }
        return account;
    }

    private Member getMemberByEmail(String memberEmail) {
        return memberService.findByEmail(memberEmail)
                .orElseThrow(() -> new AccountException("존재하지 않는 회원 정보입니다."));
    }
}
