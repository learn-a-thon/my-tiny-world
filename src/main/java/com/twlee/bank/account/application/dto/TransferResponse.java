package com.twlee.bank.account.application.dto;

import java.math.BigDecimal;

public record TransferResponse(String accountNumber, BigDecimal cash) {
}
