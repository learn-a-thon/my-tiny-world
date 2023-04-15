package com.twlee.bank.member.ui;

import com.twlee.bank.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MemberAcceptanceTest extends AcceptanceTest {
    public static String NAME = "홍길동";
    public static String EMAIL = "gildong@gmail.com";
    public static String PASSWORD = "gildong123";

    @Test
    void 회원을_생성한다() {
        // given
        // when
        var saveResponse = 회원_생성_요청(NAME, EMAIL, PASSWORD);
        // then
        회원_생성_요청_응답을_확인한다(saveResponse);
    }

    @Test
    void 회원_정보를_조회한다() {
        // given
        var saveResponse = 회원_생성_요청(NAME, EMAIL, PASSWORD);
        // when
        var findResponse = 회원_정보_조회_요청(saveResponse);
        // then
        회원_정보를_확인한다(findResponse);
    }

    @Test
    void 회원을_삭제한다() {
        // given
        var saveResponse = 회원_생성_요청(NAME, EMAIL, PASSWORD);
        // when
        var deleteResponse = 회원_삭제_요청(saveResponse);
        // then
        회원_삭제_요청_응답을_확인한다(deleteResponse);
    }

    public static ExtractableResponse<Response> 회원_생성_요청(String name, String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/members")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 회원_삭제_요청(ExtractableResponse<Response> response) {
        String uri = response.header("Location");
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().delete(uri)
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 회원_정보_조회_요청(ExtractableResponse<Response> response) {
        String uri = response.header("Location");
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get(uri)
                .then().log().all().extract();
    }

    public static void 회원_생성_요청_응답을_확인한다(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static void 회원_삭제_요청_응답을_확인한다(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    public static void 회원_정보를_확인한다(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("id")).isNotNull();
        assertThat(response.jsonPath().getString("name")).isEqualTo(NAME);
        assertThat(response.jsonPath().getString("email")).isEqualTo(EMAIL);
        assertThat(response.jsonPath().getString("password")).isEqualTo(PASSWORD);
    }
}