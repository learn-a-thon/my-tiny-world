package com.example.coupon.coupon.controller;

import com.example.coupon.coupon.dto.CouponRequest;
import com.example.coupon.coupon.service.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/coupon")
    public ResponseEntity createCoupon(@RequestBody CouponRequest couponRequest){
        couponService.createCoupon(couponRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
