package com.yunbok.searchapi.v1.common.dto;


/**
 * 이 인터페이스를 구현하는 클래스들은 다음과 같은 변수를 필수적으로 포함해야 함
 * - code : API 응답 코드 (int)
 * - message : API 응답 메시지 (String)
 */
public interface Response {
    int getCode();
    String getMessage();
}
