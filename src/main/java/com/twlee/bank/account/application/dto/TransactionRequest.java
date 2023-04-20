package com.twlee.bank.account.application.dto;

import java.math.BigDecimal;

public record TransactionRequest(String accountNumber, BigDecimal amount) {
}
