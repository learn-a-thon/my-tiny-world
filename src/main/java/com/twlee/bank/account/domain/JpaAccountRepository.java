package com.twlee.bank.account.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends AccountRepository, AccountQueryRepository, JpaRepository<Account, AccountNumber> {
}
