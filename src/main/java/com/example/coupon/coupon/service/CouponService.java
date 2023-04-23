package com.example.coupon.coupon.service;

import com.example.coupon.coupon.domain.Coupon;
import com.example.coupon.coupon.dto.CouponRequestDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CouponService {
    public void createCoupon(CouponRequestDto couponRequestDto) {
        Coupon coupon = Coupon.create(couponRequestDto.getGoodsNo(),createSerial());
    }

    private String createSerial() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 4;
        Random random = new Random();
        StringBuilder generatedString = new StringBuilder(random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString());

        for (int i=0;i<4;i++){
            generatedString.append(random.nextInt(10000));
        }

        return generatedString.toString();
    }
}
