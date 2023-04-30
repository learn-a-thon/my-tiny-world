package com.example.coupon.coupon.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;

        import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.Mockito.*;

class CouponServiceTest {

    @Mock
    private Random random;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("쿠폰번호 생성 문자4자리 + 숫자12자리")
    public void testRandomStringGeneration() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 4;

        when(random.ints(leftLimit, rightLimit + 1))
                .thenReturn(IntStream.of(97, 98, 99, 100));
        when(random.nextInt(10000))
                .thenReturn(1234)
                .thenReturn(5678)
                .thenReturn(9876)
                .thenReturn(5432);

        StringBuilder generatedString = new StringBuilder();

        for (int i = 0; i < targetStringLength; i++) {
            generatedString.append((char) random.nextInt(rightLimit - leftLimit + 1) + leftLimit);
        }

        for (int i = 0; i < 4; i++) {
            generatedString.append(random.nextInt(10000));
        }

        String expectedString = "abcd1234567898765432";
        String actualString = generatedString.toString();

        assertEquals(expectedString, actualString);
        verify(random, times(4)).nextInt(10000);
    }
}