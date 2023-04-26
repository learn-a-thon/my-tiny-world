package com.twlee.bank.account.application.dto;

import java.math.BigDecimal;

public record AccountResponse(String memberName, String memberEmail, String accountNumber, BigDecimal cash) {
}
