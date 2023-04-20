package com.twlee.bank.account.domain;

import java.util.Optional;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findByAccountNumber(String accountNumber);
}
