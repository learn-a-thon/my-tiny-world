package com.example.notification.exception;

import com.example.exception.NotFoundNotificationException;
import com.example.notification.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(getErrorResponse(httpStatus, e));
    }

    @ExceptionHandler
    private ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(httpStatus).body(getErrorResponse(httpStatus, e));
    }

    @ExceptionHandler
    private ResponseEntity<?> handleSQLSyntaxErroException(SQLSyntaxErrorException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(httpStatus).body(getErrorResponse(httpStatus, e));
    }

    @ExceptionHandler
    private ResponseEntity<?> handleInvalidDataAccessException(InvalidDataAccessResourceUsageException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(httpStatus).body(getErrorResponse(httpStatus, e));
    }

    @ExceptionHandler
    private ResponseEntity<?> handleNotFoundNotificationException(NotFoundNotificationException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(httpStatus).body(getErrorResponse(httpStatus, e));
    }


    private ErrorResponse getErrorResponse(HttpStatus httpStatus, Exception e) {
        log.error(e.getMessage());
        return ErrorResponse.builder(e, httpStatus, getMessage(ExceptionMessage.getResponseMessageBy(httpStatus), e)).build();
    }

    private String getMessage(String message, Exception e) {
        return CommonUtil.isEmpty(message) ? e.getMessage() : message;
    }

}
