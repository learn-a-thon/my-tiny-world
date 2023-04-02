package com.example.notification.logger;

import org.junit.jupiter.api.Test;

import java.util.List;
class KvDataTest {

    @Test
    public void printKvDataTest() {
        KvData kvData = KvData.of("123", List.of("a", "b", "c"));
        System.out.println(kvData);
    }
}