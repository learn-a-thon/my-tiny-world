package com.yunbok.searchapi.v1.common.exception;

import com.yunbok.searchapi.v1.common.define.ResponseCode;

public class ApiClientException extends RuntimeException {

    private ResponseCode responseCode;

    public ApiClientException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }
}
