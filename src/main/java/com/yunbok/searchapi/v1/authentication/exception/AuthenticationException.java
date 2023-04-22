package com.yunbok.searchapi.v1.authentication.exception;

import com.yunbok.searchapi.v1.common.define.ResponseCode;
import com.yunbok.searchapi.v1.common.exception.ApiServerException;
import lombok.Getter;

@Getter
public class AuthenticationException extends ApiServerException {

    public AuthenticationException(ResponseCode responseCode) {
        super(responseCode);
    }

    public AuthenticationException(String message, ResponseCode responseCode) {
        super(message, responseCode);
    }
}
