package com.yunbok.searchapi.v1.authentication.exception;

import com.yunbok.searchapi.v1.common.define.ResponseCode;
import com.yunbok.searchapi.v1.common.exception.ApiServerException;
import lombok.Getter;

@Getter
public class ApiAuthenticationException extends ApiServerException {

    public ApiAuthenticationException(ResponseCode responseCode) {
        super(responseCode);
    }

    public ApiAuthenticationException(String message, ResponseCode responseCode) {
        super(message, responseCode);
    }
}
