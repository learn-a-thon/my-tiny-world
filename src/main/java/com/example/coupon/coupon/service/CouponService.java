package com.example.coupon.coupon.service;

import com.example.coupon.coupon.domain.Coupon;
import com.example.coupon.coupon.dto.CouponRequest;
import com.example.coupon.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public void createCoupon(CouponRequest couponRequest) {
        Coupon coupon = Coupon.create(couponRequest.getGoodsNo());
        couponRepository.save(coupon);
    }
}
