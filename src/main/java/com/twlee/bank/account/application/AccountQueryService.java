package com.twlee.bank.account.application;

import com.twlee.bank.account.application.dto.AccountResponse;
import com.twlee.bank.account.domain.Account;
import com.twlee.bank.account.domain.AccountNumber;
import com.twlee.bank.account.domain.AccountQueryRepository;
import com.twlee.bank.account.exception.AccountException;
import com.twlee.bank.member.application.MemberService;
import com.twlee.bank.member.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountQueryService {
    private final AccountQueryRepository accountQueryRepository;
    private final MemberService memberService;

    public AccountQueryService(AccountQueryRepository accountQueryRepository, MemberService memberService) {
        this.accountQueryRepository = accountQueryRepository;
        this.memberService = memberService;
    }

    public AccountResponse get(String memberEmail, String accountNumber) {
        Member member = memberService.getMemberByEmail(memberEmail);
        Account account = getMemberAccount(memberEmail, accountNumber);
        return new AccountResponse(member.getName(), member.getEmail(), account.getAccountNumber(), account.getCash());
    }

    public Account getAccountByAccountNumberWithPessimisticLock(String accountNumber) {
        return accountQueryRepository.findByAccountNumberWithPessimisticLock(new AccountNumber(accountNumber))
                .orElseThrow(() -> new AccountException("존재하지 않는 계좌 정보입니다."));
    }

    public Account getMemberAccount(String memberEmail, String accountNumber) {
        Member member = memberService.getMemberByEmail(memberEmail);
        Account account = getAccountByAccountNumberWithPessimisticLock(accountNumber);
        if (!account.isOwner(member.getId())) {
            throw new AccountException("잘못된 접근입니다.");
        }
        return account;
    }
}
