package com.twlee.bank.account.ui;

import com.twlee.bank.account.application.AccountService;
import com.twlee.bank.account.application.dto.TransactionRequest;
import com.twlee.bank.common.annotation.AuthenticateAccess;
import com.twlee.bank.common.application.dto.AuthMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@AuthenticateAccess AuthMember authMember) {
        String accountNumber = accountService.create(authMember.getEmail());
        return ResponseEntity
                .created(URI.create("/accounts/" + accountNumber))
                .build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> deposit(@AuthenticateAccess AuthMember authMember,
                                        @RequestBody TransactionRequest transactionRequest) {
        accountService.deposit(authMember.getEmail(), transactionRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdraw(@AuthenticateAccess AuthMember authMember,
                                         @RequestBody TransactionRequest transactionRequest) {
        accountService.withdraw(authMember.getEmail(), transactionRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticateAccess AuthMember authMember, @RequestParam String accountNumber) {
        accountService.delete(authMember.getEmail(), accountNumber);
        return ResponseEntity.noContent().build();
    }
}
