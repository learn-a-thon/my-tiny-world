package com.example.notification.logger;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class KvData implements Data {
    private static final String DELIMITER = "=";
    private final String key;
    private final Object value;

    public static KvData of(String key, Object value) {
        return new KvData(key, value);
    }

    @Override
    public String toString() {
        return key + DELIMITER + value;
    }
}
