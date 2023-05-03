package com.twlee.bank.account.ui;

import com.twlee.bank.account.application.AccountQueryService;
import com.twlee.bank.account.application.dto.AccountResponse;
import com.twlee.bank.common.annotation.AuthenticateAccess;
import com.twlee.bank.common.application.dto.AuthMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/accounts")
public class AccountQueryController {
    private final AccountQueryService accountQueryService;

    public AccountQueryController(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> get(@AuthenticateAccess AuthMember authMember,
                                               @PathVariable String accountNumber) {
        return ResponseEntity.ok(accountQueryService.get(authMember.getEmail(), accountNumber));
    }
}
