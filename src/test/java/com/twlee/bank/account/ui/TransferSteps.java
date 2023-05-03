package com.twlee.bank.account.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TransferSteps {

    public static ExtractableResponse<Response> 송금_요청(String token, String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        Map<String, Object> params = new HashMap<>();
        params.put("fromAccountNumber", fromAccountNumber);
        params.put("toAccountNumber", toAccountNumber);
        params.put("amount", amount);
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .body(params)
                .when().post("/accounts/transfer")
                .then().log().all().extract();
    }
}
