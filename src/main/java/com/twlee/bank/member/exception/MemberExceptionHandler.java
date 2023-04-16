package com.twlee.bank.member.exception;

import com.twlee.bank.common.exception.BaseExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class MemberExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<Void> handleMemberException(MemberException e) {
        return ResponseEntity.badRequest().build();
    }
}
