package com.example.coupon.coupon.scheduler;

import com.example.coupon.coupon.domain.Coupon;
import com.example.coupon.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponScheduler {

    private final CouponRepository couponRepository;
    /**
     * 매월 1일 00:05 쿠폰 생성 스케쥴러
     */
    @Scheduled(cron = "0 5 0 1 * *")
    public void monthlyCreateCoupon(){
        int monthlyGiftGoodsNo = 1;
        couponRepository.save(Coupon.create(monthlyGiftGoodsNo));
    }

}
