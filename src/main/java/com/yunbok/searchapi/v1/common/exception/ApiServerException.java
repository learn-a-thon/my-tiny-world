package com.yunbok.searchapi.v1.common.exception;

import com.yunbok.searchapi.v1.common.define.ResponseCode;

public class ApiServerException extends RuntimeException {
    private ResponseCode responseCode;

    public ApiServerException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public ApiServerException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }
}
