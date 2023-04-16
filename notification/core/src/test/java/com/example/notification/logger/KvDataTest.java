package com.example.notification.logger;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class KvDataTest {
    private static final String TEST_KEY = "test_key";
    private static final List<String> TEST_VALUES = List.of("a", "b", "c");

    @Test
    public void printKvDataTest() {
        KvData kvData = KvData.of(TEST_KEY, TEST_VALUES);
        String expectedOutput = TEST_KEY + "=" + TEST_VALUES.toString();
        String actualOutput = kvData.toString();

        System.out.println(actualOutput);
        assertThat(actualOutput, is(expectedOutput));
    }
}