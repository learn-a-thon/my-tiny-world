package com.example.coupon.coupon.service;

import com.example.coupon.coupon.domain.Coupon;
import com.example.coupon.coupon.dto.CouponRequest;
import com.example.coupon.coupon.repository.CouponRepository;
import com.example.coupon.slack.service.SlackService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final SlackService slackService;

    public void createCoupon(CouponRequest couponRequest) {
        Coupon coupon = Coupon.create(couponRequest.getGoodsNo());
        while(couponRepository.existBySerial(coupon.getSerial())){
            coupon=Coupon.create(couponRequest.getGoodsNo());
            slackService.sendSlackMessage("동일 쿠폰번호 생성 확인하세요");
        }
        couponRepository.save(coupon);
    }

}
