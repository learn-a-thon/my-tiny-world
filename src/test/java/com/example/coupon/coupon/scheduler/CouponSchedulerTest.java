package com.example.coupon.coupon.scheduler;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponSchedulerTest {

    @Autowired
    private CouponScheduler couponScheduler;
    @Test
    @DisplayName("스케쥴러 실행 후 쿠폰생성확인을 위한 테스트 코드")
    void testMonthlyCreateCoupon() {
        couponScheduler.monthlyCreateCoupon();
    }

}