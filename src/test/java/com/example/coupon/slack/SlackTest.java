package com.example.coupon.slack;

import com.example.coupon.coupon.service.CouponService;
import com.example.coupon.slack.service.SlackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SlackTest {

    @Autowired
    private SlackService slackService;
    private CouponService couponService;
    @Test
    void 슬랙연동테스트(){
        slackService.sendSlackMessage("test");
    }

}
