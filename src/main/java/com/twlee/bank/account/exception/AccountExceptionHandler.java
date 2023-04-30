package com.twlee.bank.account.exception;

import com.twlee.bank.common.exception.BaseExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<Void> handleAccountException(AccountException e) {
        return ResponseEntity.badRequest().build();
    }
}
