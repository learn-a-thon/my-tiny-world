package com.twlee.bank.account.infra;

import com.twlee.bank.account.domain.AccountNumberCreator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDAccountNumberCreator implements AccountNumberCreator {

    @Override
    public String create() {
        return UUID.randomUUID().toString();
    }
}
