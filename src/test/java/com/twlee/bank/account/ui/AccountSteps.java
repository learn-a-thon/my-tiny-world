package com.twlee.bank.account.ui;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AccountSteps {

    public static ExtractableResponse<Response> 계좌_생성_요청(String token) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .when().post("/accounts")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 입금_요청(String token, String accountNumber, BigDecimal amount) {
        Map<String, Object> params = 계좌_수정_요청_생성(accountNumber, amount);
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .body(params)
                .when().post("/accounts/deposit")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 출금_요청(String token, String accountNumber, BigDecimal amount) {
        Map<String, Object> params = 계좌_수정_요청_생성(accountNumber, amount);
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .body(params)
                .when().post("/accounts/withdraw")
                .then().log().all().extract();
    }

    private static Map<String, Object> 계좌_수정_요청_생성(String accountNumber, BigDecimal amount) {
        Map<String, Object> params = new HashMap<>();
        params.put("accountNumber", accountNumber);
        params.put("amount", amount);
        return params;
    }

    public static ExtractableResponse<Response> 계좌_조회_요청(String token, ExtractableResponse<Response> createdResponse) {
        String uri = createdResponse.header("Location");
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .when().get(uri)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 계좌_조회_요청(String token, String accountNumber) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .when().get("/accounts/" + accountNumber)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 계좌_삭제_요청(String token) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(token)
                .when().delete("/accounts")
                .then().log().all().extract();
    }
}
